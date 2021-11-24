package com.example.admin.controller;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.admin.service.BankService;
import com.example.model.AccountRequestInfo;
import com.example.model.AccountResponseInfo;
import com.example.model.Bank;
import com.example.model.TransactionLogs;
import com.example.pdf.PdfGenerator;

/*
 * Created by Mahesh Karna
 */
@RestController
@RequestMapping("/admin")
public class BankController {

	@Autowired
	private BankService bankService;

	@Autowired
	private PdfGenerator pdfgenerator;

	@PostMapping("/createAcc")
	public ResponseEntity<AccountResponseInfo> createAccount(@RequestBody AccountRequestInfo requestInfo) {
		AccountResponseInfo responseInfo = bankService.createAccount(requestInfo);

		return ResponseEntity.status(HttpStatus.OK).body(responseInfo);

	}

	@GetMapping("/getTxLogs/{accno}")
	public ResponseEntity<List<TransactionLogs>> getTxLogs(@PathVariable String accno) {
		List<TransactionLogs> list = bankService.getLogs(accno);
		System.out.println("logs size +    " + list.size());
		return ResponseEntity.status(HttpStatus.OK).body(list);

	}

	@PutMapping("/blockaccount")
	public ResponseEntity<String> blockAccount(@RequestBody Bank bank) {
		String status = bankService.blockAccount(bank);

		return ResponseEntity.status(HttpStatus.OK).body(status);

	}

	@PutMapping("/updateaccount")
	public ResponseEntity<Bank> updateAccount(@RequestBody Bank bank) {
		Bank resultbank = bankService.updateAccountDetails(bank);
		return ResponseEntity.status(HttpStatus.OK).body(resultbank);

	}

	@DeleteMapping("/deletedata/{accno}")
	public ResponseEntity<String> deleteAccount(@PathVariable String accno) {
		String status = bankService.deleteAccount(accno);
		return ResponseEntity.status(HttpStatus.OK).body(status);
	}

	@GetMapping(value = "/downloadpdf/{accno}")
	public String downloadPdf(@PathVariable String accno, ModelMap model) {
		List<TransactionLogs> list = bankService.getLogs(accno);
		model.addAttribute("logs", list);

		return "pdf.html";
	}

	@RequestMapping(value = "/pdfreport/{accno}", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> citiesReport(@PathVariable String accno) {
		ByteArrayInputStream bis = bankService.downloadPdf(accno);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=tx_logs.pdf");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));
	}

}
