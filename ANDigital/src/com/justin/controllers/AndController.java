/*
 *************************************************
File name:		AndController.java
Author:			Justin
Date Created:	20-Mar-2016
Purpose:		



 *************************************************
 */

package com.justin.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import fi.foyt.foursquare.api.FoursquareApi;
import fi.foyt.foursquare.api.FoursquareApiException;
import fi.foyt.foursquare.api.Result;
import fi.foyt.foursquare.api.entities.CompactVenue;
import fi.foyt.foursquare.api.entities.VenuesSearchResult;

@Controller
public class AndController
{

	private static final String	CLIENT_ID			= "GEHA12JAWJUZJFN5CQKW3ZKQGYUYR4VKNDRPA0ROEJKMZ22T";
	private static final String	CLIENT_SECRET	= "XNV2SJJONOPSMKGXWIFLQFB0Y3CCQYL4SKSIWWGW2RNJGPWS";
	private static final String	REDIRECT_URI	= "http://www.justin_4s.com/redirect";
	private static final int RESULT_SCCESS = 200;

	@RequestMapping("/welcome")
	public ModelAndView welcome(HttpServletRequest aRequest)
	{
		
		String myPlace = aRequest.getParameter("placeName");
		if(myPlace!=null)
		{
			myPlace = myPlace.toLowerCase();
			FoursquareApi myFourSquare = new FoursquareApi(CLIENT_ID, CLIENT_SECRET, REDIRECT_URI);
			if (myFourSquare != null)
			{
				try
				{
					Result<VenuesSearchResult> myResult = myFourSquare.venuesSearch(myPlace, null, null, null, null, null, null,
						null, null, null, null, null, null);
					if(myResult!=null && myResult.getMeta().getCode() == RESULT_SCCESS)
					{
						StringBuffer myStringBuffer = new StringBuffer("You can visit the following here : \\n");
						for(CompactVenue venue: myResult.getResult().getVenues())
						{
							myStringBuffer.append("<h1>");
							myStringBuffer.append(venue.getName() + "//t" + venue.getLocation());
							myStringBuffer.append("</h1>");
						}
						return new ModelAndView("welcome", "message",myStringBuffer.toString());
					}
				}
				catch (FoursquareApiException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return new ModelAndView("welcome", "message", "invalid place" );
	}
}
