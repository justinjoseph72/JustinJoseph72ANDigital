/*
*************************************************
File name:		AndController.java
Author:			Justin
Date Created:	20-Mar-2016
Purpose:		



*************************************************
*/

package com.justin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AndController {

	@RequestMapping("/welcome")
	public ModelAndView welcome()
	{
		String message = "Hi there";
		return new ModelAndView("welcome","message",message);
	}
}
