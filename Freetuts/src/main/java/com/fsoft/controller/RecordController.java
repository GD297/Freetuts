package com.fsoft.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fsoft.dto.RecordDTO;
import com.fsoft.service.RecordService;

import reactor.core.publisher.Flux;

@Controller
public class RecordController {

	@Autowired
	RecordService recordService;
	
	@ResponseBody
	@RequestMapping(value="/loadnoti", method = RequestMethod.POST)
	public String loadNoti(Model model) {
		List<RecordDTO> listRecord = recordService.loadNewestRecord();	
		model.addAttribute("LIST_RECORD", listRecord);
		return "SUCCESS";
	}
	
	@GetMapping("/recordList")
	public ResponseEntity<List<RecordDTO>> getCompanyList() {
		return new ResponseEntity<List<RecordDTO>>(recordService.loadNewestRecord(), HttpStatus.OK);
	}

    @GetMapping(path = "/updateListRecord", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<RecordDTO> notiStream() {
        return recordService.loadAllRecord();
    }
	
}
