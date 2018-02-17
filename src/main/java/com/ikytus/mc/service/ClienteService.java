package com.ikytus.mc.service;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ikytus.mc.domain.Cidade;
import com.ikytus.mc.domain.Cliente;
import com.ikytus.mc.domain.Endereco;
import com.ikytus.mc.domain.enums.Perfil;
import com.ikytus.mc.domain.enums.TipoCliente;
import com.ikytus.mc.dto.ClienteDTO;
import com.ikytus.mc.dto.ClienteNewDTO;
import com.ikytus.mc.repository.CidadeRepository;
import com.ikytus.mc.repository.ClienteRepository;
import com.ikytus.mc.repository.EnderecoRepository;
import com.ikytus.mc.service.exceptions.AutorizationException;
import com.ikytus.mc.service.exceptions.DataIntegrityException;
import com.ikytus.mc.service.exceptions.ObjectNotFoundException;
import com.ikytus.mc.util.security.UserSS;
import com.ikytus.mc.util.security.UserService;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository objRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private S3Service s3Service;
		
	public Cliente find(Long id) {
		
		UserSS user = UserService.authenticated();
		if(user==null|| !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new AutorizationException("Acesso negado");
		}
		
		Cliente obj = objRepository.findOne(id); 
		
		if(obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id
					+ ", Tipo: " + Cliente.class.getName());
		}
		return obj; 
	}
	
	public List<Cliente> findAll(){
		return (List<Cliente>) objRepository.findAll();
	}
	
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = objRepository.save(obj);
		enderecoRepository.save(obj.getEnderecos());
		return obj;
	}
	
	public Cliente update(Cliente obj) {
		Cliente newCliente = find(obj.getId());
		updateData(newCliente,obj);
		return objRepository.save(newCliente);
	}
	
	public void delete (Long id) {
		find(id);
		try {
			objRepository.delete(id);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque há pedidos relacionados");
		}
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction),orderBy);
		
		return objRepository.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteDTO objDTO) {
		return new Cliente(objDTO.getId(),objDTO.getNome(),objDTO.getEmail(),null,null,null);
	}
	
	public Cliente fromDTO(ClienteNewDTO objDto) {
		Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), TipoCliente.toEnum(objDto.getTipo()),pe.encode(objDto.getSenha()));
		Cidade cid = cidadeRepository.findOne(objDto.getCidade_id());
		Endereco end =new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cli, cid);
		
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDto.getTelefone1());
		if(objDto.getTelefone2()!=null) {
			cli.getTelefones().add(objDto.getTelefone2());
		}
		if(objDto.getTelefone3()!=null) {
			cli.getTelefones().add(objDto.getTelefone3());
		}
		return cli;
	}
	
	private void updateData(Cliente newCliente, Cliente obj) {
		newCliente.setNome(obj.getNome());
		newCliente.setEmail(obj.getEmail());
	}
	
	public URI uploadProfilePicture(MultipartFile multipartFile) {
		return s3Service.uploadFile(multipartFile);
	}
}
