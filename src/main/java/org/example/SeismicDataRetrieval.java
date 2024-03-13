package org.example;

import edu.iris.dmc.criteria.CriteriaException;
import edu.iris.dmc.criteria.EventCriteria;
import edu.iris.dmc.criteria.EventOrder;
import edu.iris.dmc.service.EventService;
import edu.iris.dmc.service.NoDataFoundException;
import edu.iris.dmc.service.ServiceNotSupportedException;
import edu.iris.dmc.service.ServiceUtil;
import edu.iris.dmc.event.model.Event;
import edu.iris.dmc.event.model.Magnitude;

import java.io.IOException;
import java.util.List;

public class SeismicDataRetrieval {

    public static void main(String[] args) throws ServiceNotSupportedException, CriteriaException, IOException, NoDataFoundException {
        ServiceUtil serviceUtil = ServiceUtil.getInstance();
        serviceUtil.setAppName("Prueba");
        EventService eventService = serviceUtil.getEventService();

        EventCriteria criteria = new EventCriteria();
        //Lat y Long de Madrid
        criteria.setLatitude(40.4165);
        criteria.setLongitude(-3.70256);
        //55Km de radio mas o menos
        criteria.setMaximumRadius(0.5);
        //criteria.orderBy(EventOrder.MAGNITUDE);
        //criteria.setStartTime();
        //criteria.setEndTime();


        List<Event> events = eventService.fetch(criteria);

        for (Event e : events) {
            System.out.println("Event: " + e.getType() + " " + e.getFlinnEngdahlRegionName() + " " + e.getFlinnEngdahlRegionCode());
            System.out.println("\t"+e.getPreferredOrigin());
            for(Magnitude magnitude:e.getMagnitudes()){
                System.out.printf("\tMag: %3.1f %s\n",
                        magnitude.getValue(),
                        magnitude.getType());
            }
        }

    }
}