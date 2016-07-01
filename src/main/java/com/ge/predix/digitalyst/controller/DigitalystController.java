package com.ge.predix.digitalyst.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Vector;
import java.util.Iterator;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ge.predix.digitalyst.entity.DigitalystModel;
import com.ge.predix.digitalyst.service.DigitalystService;
import com.ge.predix.digitalyst.entity.Caller;
import com.ge.predix.digitalyst.entity.ERDataStore;
import com.plivo.helper.exception.PlivoException;
import com.plivo.helper.xml.elements.GetDigits;
import com.plivo.helper.xml.elements.Play;
import com.plivo.helper.xml.elements.PlivoResponse;
import com.plivo.helper.xml.elements.Speak;
import com.plivo.helper.xml.elements.Wait;
import com.plivo.helper.api.client.RestAPI; 
import com.plivo.helper.api.response.response.Record; 
import com.plivo.helper.api.response.message.MessageResponse;

/**
 *
 * @author
 *
 * Digitalyst  Controller : Responsible for handling all
 * participants requests.
 */

@RestController
public class DigitalystController {

	String THANK_YOU_MESSAGE = new String(". Thank you for using G E Smart Outage Voice  Assistant. Powered  by  Predix.");

	private static Log log = LogFactory.getLog(DigitalystController.class);

	@Autowired
	private com.ge.predix.digitalyst.service.DigitalystService thisCallerService;
	

/**
	* Title: homeheader
	* URL : /homeheader (= homeheader)
	* Method : GET
	* URL Param :
	* Headers :
	* Response : True : Show headers
	*   False : Play Error message.
	*/
	@RequestMapping(value = "/homeheader", method = RequestMethod.GET, produces = { "application/xml", "text/xml" })
	public Object homeheader(
            @RequestHeader(value="Accept") String accept,
            @RequestHeader(value="Accept-Language") String acceptLanguage,
            @RequestHeader(value="User-Agent", defaultValue="foo") String userAgent,
            HttpServletResponse response) {
 
        
		System.out.println("accept: " + accept);
        System.out.println("acceptLanguage: " + acceptLanguage);
        System.out.println("userAgent: " + userAgent);
		return null;
	}
	
/**
	* Title: Welcome
	* URL : /ligther (= lightER)
	* Method : POST
	* URL Param :
	* Headers :
	* Response : True : Present IVR Menu
	*   False : Play Error message.
	*/
	@RequestMapping(value = "/lighter", method = RequestMethod.GET, produces = { "application/xml", "text/xml" })
	public Object welcome(@RequestParam(value="From") String from) {

	String IVR_WELCOME_MESSAGE = "";
	String knownName = null;
	//Replace this with code to get Users from Postgres



	HashMap users = new HashMap();
	users.put("40469216"," Amar ");
	users.put("919731988776"," Amar Patil ");
	users.put("40469217"," Ganesh ");
	
	if(users.containsKey(from))
		knownName = (String)users.get(from);
	//Get the user details from the DB and append into the welcome message
	StringBuffer strBufWelcomeMessage = new StringBuffer("  Greetings of the day. Hello ");
	String fromNumber = from;
        if (knownName == null) {
        	strBufWelcomeMessage.append(" User ");
        } else {
        	strBufWelcomeMessage.append(knownName);
        }


		strBufWelcomeMessage.append(" . Welcome to G E Smart Outage Voice  Assistant. Powered  by  Predix.  Press 1 to list engineering requests for an equipment. Press 2 to check status details of an E R. Press 9 to play the menu again. To end the call, hang up");
		IVR_WELCOME_MESSAGE = strBufWelcomeMessage.toString();
		String NO_INPUT_MESSAGE = "Sorry, I did not catch that. Please hangup and try again later. Thank You.";

        PlivoResponse response = new PlivoResponse();
        String returnString = "";

        GetDigits gd = new GetDigits();

        gd.setAction("http://digitalyst-participant-103013631.run.aws-usw02-pr.ice.predix.io/ivr?From="+from);
        gd.setMethod("POST");
        gd.setNumDigits(1);
        gd.setTimeout(7);
        gd.setRetries(1);
        Speak spk = new Speak(IVR_WELCOME_MESSAGE);
        Speak speak = new Speak(NO_INPUT_MESSAGE);

        try {
            gd.append(spk);
            response.append(gd);
            response.append(speak);
            System.out.println(response.toXML());
            //resp.addHeader("Content-Type", "text/xml");
            //resp.getWriter().print(response.toXML());;
        } catch (PlivoException e) {
            e.printStackTrace();
        }

        returnString = response.toXML();
        return returnString;
	}

	/**
	 * Title: IVR Menu
	 * URL : /ivr
	 * Method : POST
	 * URL Param :
	 * Headers :
	 * Response : True : Redirect to Search ESN result response OR Play PAC options
	 * 			  False : Play Error message.
	 */
	@RequestMapping(value = "/ivr", method = RequestMethod.POST, produces = { "application/xml", "text/xml" }, consumes = MediaType.ALL_VALUE)
	public Object ivrMenu(@RequestParam(value="Digits") String digit) {


		log.info("Serving erivr resource request with values digits="+ digit);

		//Process the digits
        PlivoResponse response = new PlivoResponse();
		String returnString = "";
		String IVR_MESSAGE = "";
		String WRONG_INPUT_MESSAGE = "Sorry, it is a wrong input. "+digit+" . Please hang up and try again";

        if (digit.equals("1")) {
			IVR_MESSAGE = "Please Enter 6 digit Equipment Serial Number";

            // Read out a text
            GetDigits gd = new GetDigits();
            gd.setAction("http://digitalyst-participant-103013631.run.aws-usw02-pr.ice.predix.io/searchesn");
            gd.setMethod("POST");
            gd.setNumDigits(6);
            gd.setTimeout(10);
            gd.setRetries(1);
            Speak spk = new Speak(IVR_MESSAGE);
            try {
                gd.append(spk);
                response.append(gd);
            } catch (PlivoException e) {
                e.printStackTrace();
            }
        }
        else if (digit.equals("2")) {
			IVR_MESSAGE = "Enter 4 digit Engineering Request I D";

            // Read out a text
            GetDigits gd = new GetDigits();
            gd.setAction("http://digitalyst-participant-103013631.run.aws-usw02-pr.ice.predix.io/searchpac");
            gd.setMethod("POST");
            gd.setNumDigits(4);
            gd.setTimeout(10);
            gd.setRetries(1);
            Speak spk = new Speak(IVR_MESSAGE);
            try {
                gd.append(spk);
                response.append(gd);
            } catch (PlivoException e) {
                e.printStackTrace();
            }
        } else {
            // Wrong input
            Speak speak = new Speak(WRONG_INPUT_MESSAGE);
            try {
                response.append(speak);
            } catch (PlivoException e) {
                e.printStackTrace();
            }
        }

        System.out.println(response.toXML());

		returnString = response.toXML();

		return returnString;
	}

	/**
	 * Title: Search ESN
	 * URL : /searchesn
	 * Method : POST
	 * URL Param :
	 * Headers :
	 * Response : True : List all PACs found
	 * 			  False : Play Error message.
	 */
	@RequestMapping(value = "/searchesn", method = RequestMethod.POST, produces = { "application/xml", "text/xml" }, consumes = MediaType.ALL_VALUE)
	public Object searchEsn(@RequestParam(value="Digits") String digit) {


		log.info("Serving erivr resource request with values digits="+ digit);

		//Process the digits
        PlivoResponse response = new PlivoResponse();
		String returnString = "";
		String IVR_MESSAGE = "";
		String WRONG_INPUT_MESSAGE = "Sorry, it's wrong input. Please hangup and try again. Thank you.";

		if (digit.length() != 6) {
			IVR_MESSAGE = "Wrong Equipment Serial Number. Please hangup and try again. Thank you.";
		} else {

			//Search ESN and fetch related PACs and list them

			ERDataStore allEr = new ERDataStore();
			List pacList = allEr.getAllERs(digit);	

			if (pacList.size() > 0) {
				//found PACs

				StringBuffer strBufMessage = new StringBuffer();
				strBufMessage.append("Found ");
				strBufMessage.append(pacList.size());
				strBufMessage.append(" Engineering Requests. These are ");
				for(int i = 0; i < pacList.size(); i++) {
					String strERID = (String)pacList.get(i);
					strBufMessage.append(strERID);
					strBufMessage.append("  ");
				}

				IVR_MESSAGE = strBufMessage.toString();
			} else {
				//no PACs

				IVR_MESSAGE = "No Engineering Requests found for Equipment Serial Number " + digit;
				//IVR_MESSAGE.concat(" Please hangup and try again. Thank you.");

			}
		}


		Speak speak = new Speak(IVR_MESSAGE);
		try {
			response.append(speak);
		} catch (PlivoException e) {
			e.printStackTrace();
		}

        System.out.println(response.toXML());

		returnString = response.toXML();

		return returnString;
	}

	/**
	 * Title: Search PAC
	 * URL : /searchpac
	 * Method : POST
	 * URL Param :
	 * Headers :
	 * Response : True : Acknowledge found. Present options
	 * 			  False : Play Error message.
	 */
	@RequestMapping(value = "/searchpac", method = RequestMethod.POST, produces = { "application/xml", "text/xml" }, consumes = MediaType.ALL_VALUE)
	public Object searchpac(@RequestParam(value="Digits") String digit) {

		log.info("Serving searchpac resource request with values digits="+ digit);

		//Process the digits

        PlivoResponse response = new PlivoResponse();
		String returnString = "";
		String IVR_MESSAGE = "";
		String WRONG_INPUT_MESSAGE = "Sorry, it's wrong input. Please hangup and try again. Thank you.";
		String NO_INPUT_MESSAGE = "Sorry, I did not catch that. Please hangup and try again. Thank you.";

		if (digit.length() != 4) {

			IVR_MESSAGE = "Wrong Engineering Request I D. Please hangup and try again. Thank you.";
		} else {

			//check in DB for the PAC
			//If found then
			boolean found = false;

			//TO DO
			//Get from DB and if found, set found - true
			ERDataStore allEr = new ERDataStore();
			found = allEr.isERPresent(digit);
			log.info("digit in searchpac is" + digit);
			if(found) {

				IVR_MESSAGE = "Found Engineering Request " + getSpacedDigits(digit) + ". Press 1 to know E R Description, Press 2 to know E R Desired Deliverable, Press 3 to know the E R Resolution Notes, Press 4 to receive E R Resolution Notes as SMS.";
				GetDigits gd = new GetDigits();

				gd.setAction("http://digitalyst-participant-103013631.run.aws-usw02-pr.ice.predix.io/pacivrtree?PacId="+digit);
				gd.setMethod("POST");
				gd.setNumDigits(1);
				gd.setTimeout(7);
				gd.setRetries(1);
				Speak spk = new Speak(IVR_MESSAGE);
				Speak speak = new Speak(NO_INPUT_MESSAGE);

				try {
					gd.append(spk);
					response.append(gd);
					response.append(speak);
					System.out.println(response.toXML());
					//resp.addHeader("Content-Type", "text/xml");
					//resp.getWriter().print(response.toXML());;
				} catch (PlivoException e) {
					e.printStackTrace();
				}
			} else {
				IVR_MESSAGE = "Engineering Request I D not found. Please hang up and try again. Thank You";
				Speak speak = new Speak(IVR_MESSAGE);
				try {
					response.append(speak);
				} catch (PlivoException e) {
					e.printStackTrace();
				}
			}
		}

        System.out.println(response.toXML());

        returnString = response.toXML();

		return returnString;
	}


	/**
	 * Title: PAC IVR Tree
	 * URL : /pacivrtree
	 * Method : POST
	 * URL Param :
	 * Headers :
	 * Response : True : Present options
	 * 			  False : Play Error message.
	 */
	@RequestMapping(value = "/pacivrtree", method = RequestMethod.POST, produces = { "application/xml", "text/xml" }, consumes = MediaType.ALL_VALUE)
	public Object pacivrtree(@RequestParam(value="Digits") String digit,
								@RequestParam(value="PacId") String pacId) {
		log.info("Serving erivr resource request with values digits="+ digit);
		String returnString = "";
		//Process the digits
		String WRONG_INPUT_MESSAGE = "Sorry, it is wrong input. Please hang up and try again. Thank You.";

         PlivoResponse response = new PlivoResponse();
         System.out.println("digit is : " +digit);
         

         if(pacId.indexOf(",") != -1)	
        	 pacId = pacId.substring(0, pacId.indexOf(","));

         if (digit.equals("1"))
         {
            //Search DB, get the description. Use pacId from the request param. Read out the description.
        	 System.out.println("pacId is : " +pacId);

        	 ERDataStore allEr = new ERDataStore();
        	 String strErDescription = new String();
 			 strErDescription = allEr.getERDesc(pacId);
 			
 			System.out.println("Description is : " +strErDescription);
            Speak speak = new Speak(strErDescription); 
             try{
                 response.append(speak);
             } catch (PlivoException e) {
                 e.printStackTrace();
             }
         }
         else if (digit.equals("2"))
         {
        	// Play the mp3
         	ERDataStore allEr = new ERDataStore();
  			String strErDeliverableUrl = new String();
  			strErDeliverableUrl = allEr.getERDeliverable(pacId);
             Play play = new Play(strErDeliverableUrl);
             try {
                 response.append(play);
             } catch (PlivoException e) {
                 e.printStackTrace();
             }
         }
         else if(digit.equals("3"))
         {
             //Use pacId from the request param Search DB, read the resolution notes. 
 			ERDataStore allEr = new ERDataStore();
 			String strResolvedNotes = new String();
 			strResolvedNotes = allEr.getERResolvedNotes(pacId);

             Speak speak = new Speak(strResolvedNotes); //Description goes here
             try{
                 response.append(speak);
             } catch (PlivoException e) {
                 e.printStackTrace();
             }
         }
         else if(digit.equals("4"))
         {
			 String IVR_MESSAGE = "To update the E R desired deliverable, Press 7";
			 String NO_INPUT_MESSAGE = "Sorry, I did not catch that. Please hangup and try again. Thank You.";

			GetDigits gd = new GetDigits();
			gd.setAction("http://digitalyst-participant-103013631.run.aws-usw02-pr.ice.predix.io/erresnotessms?PacId="+digit+"&From=");
//			gd.setAction("http://digitalyst-participant-103013631.run.aws-usw02-pr.ice.predix.io/erresnotessms?PacId="+digit+"&From="+from);
//			gd.setAction("http://digitalyst-participant-103013631.run.aws-usw02-pr.ice.predix.io/updatepac?PacId="+digit);
			gd.setMethod("POST");
			gd.setNumDigits(1);
			gd.setTimeout(7);
			gd.setRetries(1);
			Speak spk = new Speak(IVR_MESSAGE);
			Speak speak = new Speak(NO_INPUT_MESSAGE);

			try {
				gd.append(spk);
				response.append(gd);
				response.append(speak);
				System.out.println(response.toXML());
				//resp.addHeader("Content-Type", "text/xml");
				//resp.getWriter().print(response.toXML());;
			} catch (PlivoException e) {
				e.printStackTrace();
			}
         }
         else if(digit.equals("5"))

         {
			 String IVR_MESSAGE = "To update the Engineering Request desired deliverable, Press 7";
			 String NO_INPUT_MESSAGE = "Sorry, I did not catch that. Please hangup and try again. Thank You.";

			GetDigits gd = new GetDigits();

			gd.setAction("http://digitalyst-participant-103013631.run.aws-usw02-pr.ice.predix.io/updatepac?PacId="+digit);
			gd.setMethod("POST");
			gd.setNumDigits(1);
			gd.setTimeout(7);
			gd.setRetries(1);
			Speak spk = new Speak(IVR_MESSAGE);
			Speak speak = new Speak(NO_INPUT_MESSAGE);

			try {
				gd.append(spk);
				response.append(gd);
				response.append(speak);
				System.out.println(response.toXML());
				//resp.addHeader("Content-Type", "text/xml");
				//resp.getWriter().print(response.toXML());;
			} catch (PlivoException e) {
				e.printStackTrace();
			}
         }
         
         else
         {
             // Wrong input
             Speak speak = new Speak(WRONG_INPUT_MESSAGE);
             try {
                 response.append(speak);
             } catch (PlivoException e) {
                 e.printStackTrace();
             }
         }


        System.out.println(response.toXML());

		returnString = response.toXML();

		return returnString;
	}


	/**
	 * Title: Update PAC Desired Deliverable
	 * URL : /updatepac


	 * Method : POST
	 * URL Param :
	 * Headers :
	 * Response : True : if model saved into DB (Status code = 200)
	 * 			  False : Error in saving the data into DB.



	 */
	@RequestMapping(value = "/updatepac", method = RequestMethod.POST, produces = { "application/xml", "text/xml" }, consumes = MediaType.ALL_VALUE)
	public Object saveDigitalystModel(@RequestParam(value="Digits") String digit,
										@RequestParam(value="PacId") String pacId) {
		log.info("Serving erivr resource request with values digits="+ digit);

		//Process the digits

        PlivoResponse response = new PlivoResponse();
		String returnString = "";

		//Store the data here
		//Use the PAC ID from request param






		// Wrong input
		Speak speak = new Speak(""); //Final message saying its stored or not
		try {
			response.append(speak);
		} catch (PlivoException e) {
			e.printStackTrace();
		}








        System.out.println(response.toXML());



		/*
		DigitalystModel digitalystModel = new DigitalystModel();
		String returnString = "participants save successfully!";
		digitalystModel.setParticipantName(participantName);
		digitalystModel.setParticipantEmail(participantEmail);
		digitalystModel.setParticipantOrg(participantOrg);






		if(true != modelService.saveDigitalystModel(digitalystModel)){
			Speak errSpeak = new Speak(IVR_ERR_MESSAGE2);
			response.append(errSpeak);
		}
		*/





		returnString = response.toXML();











		return returnString;









	}

	/**
     * Title: Record Message
     * URL : /updatedeliverable
     * Method : POST
     * URL Param :
     * Headers :
     * Response : True : Redirect to store the incoming call
     *                                              False : Play Error message.
     */


     @RequestMapping(value = "/updatedeliverable", method = RequestMethod.POST, produces = { "application/xml", "text/xml" }, consumes = MediaType.ALL_VALUE)
     public Object updatedeliverable(@RequestParam(value="Digits") String digits) {

                     log.info("Serving erivr resource request with values digits="+ digits);

                     //Process the digits

PlivoResponse response = new PlivoResponse();
String returnString = "";
String IVR_MESSAGE = "Press 1 to record this call";

GetDigits gd = new GetDigits();
gd.setAction("https://dry-fortress-4047.herokuapp.com/record");
gd.setMethod("GET");
gd.setTimeout(7);
gd.setNumDigits(1);
gd.setRetries(1);
gd.setRedirect(false);

Speak spk = new Speak(IVR_MESSAGE);
Wait wait = new Wait();
wait.setLength(10);

try {
 gd.append(spk);
 response.append(gd);
 response.append(wait);
 System.out.println(response.toXML());
 //resp.addHeader("Content-Type", "text/xml");
 //resp.getWriter().print(response.toXML());;
} catch (PlivoException e) {
 e.printStackTrace();
}

System.out.println(response.toXML());

                     returnString = response.toXML();

                     return returnString;
     }
	

     /**
     * Title: Record Voice Message - Desired Deliverable
     * URL : /record
     * Method : POST
     * URL Param :
     * Headers :
     * Response : True : Record, store and a URL returned
     *                                              False : Play error.
     * https://www.plivo.com/docs/getting-started/record-api/
     */
     @RequestMapping(value = "/record", method = RequestMethod.POST, produces = { "application/xml", "text/xml" }, consumes = MediaType.ALL_VALUE)
     public void record(@RequestParam(value="Digits") String digit,
                        @RequestParam(value="CallUUID") String callUUID,
                        HttpServletResponse resp) {

log.info("Serving erivr resource request with values digits="+ digit);







try{
resp.getWriter().print("Digit : " + digit + " Call UUID : " + callUUID);
}catch(java.io.IOException e)
{
	System.out.println(e.getMessage());
}
String auth_id = "Your AUTH_ID";
String auth_token = "Your AUTH_TOKEN";




RestAPI api = new RestAPI(auth_id, auth_token, "v1");








if(digit.equals("1"))
{
 LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
 parameters.put("call_uuid",callUUID);
 try
 {
     Record record = api.record(parameters);
     System.out.println(record);




     //TO DO
     //Store the url intoo the deliverable
     String recordingLocation = record.url;

     //TO DO
     //Take this and use it in the play function
 }
 catch (PlivoException e)
 {
     System.out.println(e.getLocalizedMessage());
 }
}
else
{
 System.out.println("Wrong Input");
}


}
		/**
	 * Title: Send ER Resolution Notes in SMS
	 * URL : /erresnotessms
	 * Method : POST
	 * URL Param :
	 * Headers :
	 * Response : True : Send SMS
	 * 			  False : Send Error SMS.
	 * https://www.plivo.com/docs/getting-started/record-api/
	 */
	@RequestMapping(value = "/erresnotessms", method = RequestMethod.POST, produces = { "application/xml", "text/xml" }, consumes = MediaType.ALL_VALUE)
	public void erresnotessms(@RequestParam(value="Digits") String digit,
						@RequestParam(value="PacId") String pacId,
						@RequestParam(value="From") String from,
										@RequestParam(value="CallUUID") String callUUID,
										HttpServletResponse resp) {

		log.info("Serving erivr resource request with values digits="+ digit);
     if(from.indexOf(",") != -1)
     	from = from.substring(0, from.indexOf(","));
     
		if(pacId.indexOf(",") != -1)
			pacId = pacId.substring(0, pacId.indexOf(","));

     ERDataStore allEr = new ERDataStore();
		String strErResNotes = new String();
		strErResNotes = allEr.getERResolvedNotes(pacId);

		String dstNumber = "919731988776";
		
		if(from != null)
			dstNumber = from;

     String authId = "MAMWVMODI3ODIXYJG3MZ";
     String authToken = "NjA0ZWI2ODBiYTkwOTIzYjEwZmMzY2VlM2M4ZTlj";

     RestAPI api = new RestAPI(authId, authToken, "v1");

     LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
     parameters.put("src", "14043309216"); // Sender's phone number with country code
     parameters.put("dst", dstNumber); // Receiver's phone number with country code

     parameters.put("text", "ER ID:" + pacId + " NOTES:" + strErResNotes); // Your SMS text message
     //TO DO - add the following deails
     parameters.put("url", "http://digitalyst-participant-103013631.run.aws-usw02-pr.ice.predix.io/lighter?From="+from); // The URL to which with the status of the message is sent
     parameters.put("method", "GET"); // The method used to call the url

     try {
         // Send the message
         MessageResponse msgResponse = api.sendMessage(parameters);

         // Print the response
         System.out.println(msgResponse);
         // Print the Api ID
         System.out.println("Api ID : " + msgResponse.apiId);
         // Print the Response Message
         System.out.println("Message : " + msgResponse.message);

         if (msgResponse.serverCode == 202) {
             // Print the Message UUID
             System.out.println("Message UUID : " + msgResponse.messageUuids.get(0).toString());
         } else {
             System.out.println(msgResponse.error);
         }
     } catch (PlivoException e) {
         System.out.println(e.getLocalizedMessage());
     }
	}
     
     
     
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(@RequestHeader(value="echo",defaultValue="echo") String echo) {
		return "hello";
	}

	private List<DigitalystModel> getUsers() {
		log.info("Serving get users resource service request");
			//List<Caller> Callers = thisCallerService.readCallerModel();
		List<DigitalystModel> Callers = thisCallerService.readDigitalystModel();
			

			return Callers;
			}

	private String getSpacedDigits(String digits) {
		StringBuffer newStrBuf = new StringBuffer();
		
		for(int i = 0; i < digits.length(); i++) 
			newStrBuf.append(digits.charAt(i) + " ");

		return newStrBuf.toString();
	}	

}