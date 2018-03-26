package com.nj.parcc.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nj.parcc.dto.MarksDTO;
import com.nj.parcc.dto.ParccResultDTO;
import com.nj.parcc.entity.MarksEntity;
import com.nj.parcc.entity.ParccResultEntity;
import com.nj.parcc.exception.ErrorMessages;
import com.nj.parcc.exception.ParccServiceException;
import com.nj.parcc.repository.IParccResultRepository;
import com.nj.parcc.service.ParccMessageService;
import com.nj.parcc.service.ParccService;
/**
 * Implementation class of {@link ParccService} interface. 
 * @author Sriram
 *
 */
@Service
public class ParccServiceImpl implements ParccService {

	@Autowired
	public ParccMessageService _parccMsgService;

	@Autowired
	private IParccResultRepository parccResultRepository;

	//Method to Transfer the ParccResultEntity to ParccResultDTO
	private Function<ParccResultEntity, ParccResultDTO> convertEntityToDTO = new Function<ParccResultEntity, ParccResultDTO>() {
		public ParccResultDTO apply(ParccResultEntity t) {
			ParccResultDTO dto = new ParccResultDTO();
			dto.setId(String.valueOf(t.getId()));
			dto.setName(t.getName());
			dto.setSchool(t.getSchool());
			List<MarksDTO> list = new ArrayList<MarksDTO>();
			for (MarksEntity mEntity : t.getMarksMaps()) {
				MarksDTO mDto = new MarksDTO();
				mDto.setId(String.valueOf(mEntity.getId()));
				mDto.setMark(String.valueOf(mEntity.getMark()));
				mDto.setSub(mEntity.getSubject());
				list.add(mDto);
			}
			dto.setMarks(list);
			return dto;
		}
	};

	@Override
	public List<ParccResultDTO> getParccResults() throws ParccServiceException {
		try {
		Iterable<ParccResultEntity> parccResults = parccResultRepository.findAll();
		return Collections.unmodifiableList(StreamSupport.stream(parccResults.spliterator(), false).map(convertEntityToDTO)
				.collect(Collectors.<ParccResultDTO>toList()));
		} catch (Exception e) {
			throw new ParccServiceException(e.getMessage());
		}
	}

	@Override
	public ParccResultDTO getParccResults(long id) throws ParccServiceException {
		try {
		return convertEntityToDTO.apply(parccResultRepository.findOne(id));
		} catch (Exception e) {
			throw new ParccServiceException(e.getMessage());
		}
	}

	@Override
	public ParccResultDTO createParccResult(ParccResultDTO parccResultDTO) throws ParccServiceException {
		ParccResultEntity pEntity = new ParccResultEntity();
		pEntity.setName(parccResultDTO.getName());
		pEntity.setSchool(parccResultDTO.getSchool());
		Set<MarksEntity> list = new LinkedHashSet<MarksEntity>();
		for (MarksDTO mDto : parccResultDTO.getMarks()) {
			MarksEntity mEntity = new MarksEntity();
			mEntity.setSubject(mDto.getSub());
			mEntity.setMark(Integer.parseInt(mDto.getMark()));
			mEntity.setParccResultEntity(pEntity);
			list.add(mEntity);
		}
		pEntity.setMarksMaps(list);
		try {
			parccResultRepository.save(pEntity);
		} catch (Exception ex) {
			throw new ParccServiceException(ex.getMessage());
		}
		parccResultDTO.setCode(ErrorMessages.PARCC_000.getCode());
		parccResultDTO.setMsg(_parccMsgService.getErrorMessage(ErrorMessages.PARCC_000));
		return parccResultDTO;
	}

	@Override
	public ParccResultDTO updateParccResult(ParccResultDTO parccResultDTO) throws ParccServiceException {
		ParccResultEntity pEntity = new ParccResultEntity();
		pEntity.setId(Long.valueOf(parccResultDTO.getId()));
		pEntity.setName(parccResultDTO.getName());
		pEntity.setSchool(parccResultDTO.getSchool());
		Set<MarksEntity> list = new LinkedHashSet<MarksEntity>();
		for (MarksDTO mDto : parccResultDTO.getMarks()) {
			MarksEntity mEntity = new MarksEntity();
			mEntity.setId(Long.valueOf(mDto.getId()));
			mEntity.setMark(Integer.valueOf(mDto.getMark()));
			mEntity.setSubject(mDto.getSub());
			mEntity.setParccResultEntity(pEntity);
			list.add(mEntity);
		}
		pEntity.setMarksMaps(list);
		try {
			parccResultRepository.save(pEntity);
		} catch (Exception ex) {
			throw new ParccServiceException(ex.getMessage());
		}
		parccResultDTO.setCode(ErrorMessages.PARCC_000.getCode());
		parccResultDTO.setMsg(_parccMsgService.getErrorMessage(ErrorMessages.PARCC_000));
		return parccResultDTO;
	}

	@Override
	public void deleteParccResult(long id) throws ParccServiceException {
		try {
			parccResultRepository.delete(id);
		}
		catch (Exception ex) {
			throw new ParccServiceException(ex.getMessage());
		}

	}

}
