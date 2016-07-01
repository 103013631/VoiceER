package com.ge.predix.digitalyst.entity;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public class ERDataStore {

	private List<String> erData1;
	private List<String> erData2;
	private List<String> erData3;
	private List<String> erData4;
	private List<String> erData5;
	private List<String> erData6;
	private List<String> erData7;
	private List<String> erData8;
	private List<String> erData9;
	private List<String> erData10;
	private List<String> erData11;	

	private HashMap<String, List> erMap;

	public ERDataStore() {

		erMap = new HashMap<String, List>();

		erData1 = new ArrayList<String>();
		

		erData1.add( "2079" );
		erData1.add( "112233" );
		erData1.add( "Hot Gas Path outage for Turbine number 0 0 1 at Hardville site in North America Region. I need support on the P A C number E R 6 1, chromatograph junction box T B 8 9 1 is not working as expected. Also which TIL is applicable and must be implemented. I have already applied the TILs as stated in PGS Portal but  issues still continue." );
		erData1.add( "http://digitalyst-participant-103013631.run.aws-usw02-pr.ice.predix.io/2079DD.mp3");
		erData1.add( "Request Engineering to review the information provided and provide site a resolution on where to connect the UPS " );
		erData1.add( "Modification to UPS implemented during 2012 repair allows clearing this field from TIL requirement. Rather than removing from AUL, please mark it as completed in the portal (you can refer to this Pac Case as justification if needed");

		erData2 = new ArrayList<String>();

		erData2.add( "2431" );
		erData2.add( "112233" );
		erData2.add( "A review of the breakdown of the inlet bellmouth does not reveal the required gaskets or bolts. The gaskets used for these flanges and the bolting hardware are not called out in the BOM for unit 7 2 7 X." );
		erData2.add( "http://digitalyst-participant-103013631.run.aws-usw02-pr.ice.predix.io/2431DD.mp3");
		erData2.add( "Provide the safety operation parameter values at site condition to propose the right recommendation to costumer " );
		erData2.add( "As per the mail communication, EPE engineering replied that the Inlet Bellmouth throat annulus is frame specific. In order to confirm the diffuser is properly aligned with the exhaust frame, please look at the whole diameter of the diffuser.  Also confirm the diffuser and exhaust frame are 12.000 inch apart as shown in Section A - 5 as well.");

		erData3 = new ArrayList<String>();

		erData3.add( "2018" );
		erData3.add( "100003" );
		erData3.add( "Site lost his Historian due to Access problem. Now they tried to restore from Last back up that we left during the " );
		erData3.add( "http://digitalyst-participant-103013631.run.aws-usw02-pr.ice.predix.io/2018DD.mp3");
		erData3.add( "Engineering need to suport site in this issue any log, etc" );
		erData3.add( "Per our conversation with Nick, it is apparent that the 5-6 mil clearance per design cannot be achieved with the ");

		erData4 = new ArrayList<String>();

		erData4.add( "2335" );
		erData4.add( "100004" );
		erData4.add( "Unit 100004 recently received TIL 1000-R1 as it appears in AUL. In 2012 the field of this Generator was sent to " );
		erData4.add( "http://digitalyst-participant-103013631.run.aws-usw02-pr.ice.predix.io/2335DD.mp3");
		erData4.add( "Could you please review the repair report and let us know if this specific Generator can be excluded from TILs 1834" );

		erData5 = new ArrayList<String>();

		erData5.add( "1390" );
		erData5.add( "100005" );
		erData5.add( "Site is planning to re-tune the ARES Model, we need a precise value of  Compressor Flow Coefficient and Bellmouth " );
		erData5.add( "http://digitalyst-participant-103013631.run.aws-usw02-pr.ice.predix.io/1390DD.mp3");
		erData5.add( "Please help to get the data" );

		erData6 = new ArrayList<String>();

		erData6.add( "1844" );
		erData6.add( "100006" );
		erData6.add( "Drawing LS and RS; the Stems Drawing 10000P1, provided by customer. The width of the stem grove are to be 2 " );
		erData6.add( "http://digitalyst-participant-103013631.run.aws-usw02-pr.ice.predix.io/1844DD.mp3");
		erData6.add( "PMT is reporting to engineering the split ring thickness deviation from design and provide comments as needed of " );

		erData7 = new ArrayList<String>();

		erData7.add( "2012" );
		erData7.add( "100007" );
		erData7.add( "Customer requested two deviations to print 100R226 for the 10 G or 1st G E bucket dimensions below Clearance " );
		erData7.add( "http://digitalyst-participant-103013631.run.aws-usw02-pr.ice.predix.io/2012DD.mp3");
		erData7.add( "MAS, please go ahead and implement the suggested modificationsMahamad");

		erData8 = new ArrayList<String>();

		erData8.add( "1504" );
		erData8.add( "100008" );
		erData8.add( "The exhaust thermocouple couple ladder is interfering with cooling & sealing air piping After trying everything else, " );
		erData8.add( "http://digitalyst-participant-103013631.run.aws-usw02-pr.ice.predix.io/1504DD.mp3");
		erData8.add( "MAS, please go ahead and implement the suggested modifications");

		erData9 = new ArrayList<String>();

		erData9.add( "4643" );
		erData9.add( "100008" );
		erData9.add( "Due to the difficutlies and potential FME risk locking the inner oil deflector bolts on this generator with locking " );
		erData9.add( "http://digitalyst-participant-103013631.run.aws-usw02-pr.ice.predix.io/4643DD.mp3");
		erData9.add( "MAS, please go ahead and implement the suggested modifications");

		erData10 = new ArrayList<String>();
		
		erData10.add( "0092" );
		erData10.add( "299333" );
		erData10.add( "This is Saudi Aramco Third Party Cogeneration Projects - Ras Tanura- Turbines 2 9 9 3 3 3 / 3 3 4 GEN. 3 3 7 X 4 8 9 / 4 9 0 - I P S 6 4 9 8 8 4. Please Refer To E R - 2 0 1 5 1 1 1 7 - 0 4 0 4. We have same issue of J B 1 6 9 like A B Q A I Q site our units are for same Customer and Need to follow same resolution on E R - 2 0 1 5 1 1 1 7 - 0 4 0 4.");
		erData10.add( "http://digitalyst-participant-103013631.run.aws-usw02-pr.ice.predix.io/DD0092.mp3");
		erData10.add( "Mohammed, site can use the resolution of E R - 2 0 1 5 1 1 1 7 - 0 4 0 4 shown below. Please refer to the attached drawing# 1 1 7 T 7 0 6 6 sheet # 3 for J B 1 6 9 location details. The tubing slope must be minimum 1 inch per foot for the 96 E P s.For rest all devices the slope should be minimum of 0.25 inch per foot. Please share the drawing with site, it contains details for installation of devices in M L I 5 5 7 T. This drawing has been created for a new project and includes only the devices and panels relevant to that unit. However, site can refer to this drawing prior to raising PAC case for M L I  5 5 7 T for their project. Mahamad");

		erData11 = new ArrayList<String>();
		
		erData11.add( "0568" );
		erData11.add( "890004" );
		erData11.add( "E T C 2 1 4 is about EXTENDED BORESCOPE PLUGS. The parts number listed in table one are not in the BOM of unit 8 9 0 0 0 4." );
		erData11.add( "http://digitalyst-participant-103013631.run.aws-usw02-pr.ice.predix.io/DD0568.mp3");
		erData11.add( "No resolve notes available yet.");
		
		erMap.put("2079", erData1);
		erMap.put("2431", erData2);
		erMap.put("2018", erData3);
		erMap.put("2335", erData4);
		erMap.put("1390", erData5);
		erMap.put("1844", erData6);
		erMap.put("2012", erData7);
		erMap.put("1504", erData8);
		erMap.put("4643", erData9);
		erMap.put("0092", erData10);
		erMap.put("0568", erData11);
		

	}

	public List getAllERs(String esn) {

		List matchingERs = new ArrayList<String>();
		List erData = null;

		Iterator it = erMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry)it.next();
			erData = (List)pair.getValue();

			if((erData.get(1)).equals(esn)) {
				matchingERs.add(erData.get(0));
			}

			it.remove(); // avoids a ConcurrentModificationException
    	}

    	return matchingERs;
	}

	public boolean isERPresent(String erId) {
		if(erMap.containsKey(erId))
			return true;
		else
			return false;
	}

	public String getERESN(String erId) {
		String esn = "";
		if(erMap.containsKey(erId)) {
			List erData = (List)erMap.get(erId);
			esn = (String)erData.get(1);
		}

		return esn;
	}

	public String getERDesc(String erId) {
		String desc = "";
		if(erMap.containsKey(erId)) {
			List erData = (List)erMap.get(erId);
			desc = (String)erData.get(2);
		}

		return desc;
	}

	public String getERDeliverable(String erId) {
		String deliverable = "";
		if(erMap.containsKey(erId)) {
			List erData = (List)erMap.get(erId);
			deliverable = (String)erData.get(3);
		}

		return deliverable;
	}

	public String getERResolvedNotes(String erId) {
		String resolvedNotes = "";
		if(erMap.containsKey(erId)) {
			List erData = (List)erMap.get(erId);
			resolvedNotes = (String)erData.get(4);
		}

		return resolvedNotes;
	}

	public boolean updateERDeliverable(String erId, String deliverable) {
			boolean updated = false;
			if(erMap.containsKey(erId)) {
				List erData = (List)erMap.get(erId);
				erData.set(3, deliverable);
				updated = true;
			}

			return updated;
	}
}