package com.MDF.firstRestApi.My_first_app.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class ApiResponse {
	
	@NonNull
	private Boolean success;
	
	@NonNull
	private String message;

	
}
