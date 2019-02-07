package mk;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

//----------------------------------- 2.0.1 -----------------------------------	
import brave.Span;
import brave.Tracer;
//----------------------------------- 2.0.1 -----------------------------------	

//----------------------------------- 1.5.10 -----------------------------------
//import org.springframework.cloud.sleuth.Span;
//import org.springframework.cloud.sleuth.Tracer;
//----------------------------------- 1.5.10 -----------------------------------


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


//import org.springframework.kafka.core.KafkaTemplate;


import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;


@Controller

class WebController {

//	@Value("${app.ajax_url}")
//	String ajax_url;

	@Autowired
	Environment env;
	
//    @Autowired	
//    private RabbitTemplate rabbitTemplate;
	

//    @Autowired
//    private KafkaTemplate<String, String> kafkaTemplate;
    
    @Autowired
	private Tracer tracer;

    
	 private static Logger log = LoggerFactory.getLogger(WebController.class);

	 
	    @GetMapping("/user")
	    public String userVIEW(@RequestParam(value = "id", defaultValue = ".") String id,Model model) {

	    		return "user/index";
	    	}
	 
    @GetMapping("/")
    public String welcomeVIEW(@RequestParam(value = "id", defaultValue = ".") String id,Model model) {
    	/*
    	   Span span=tracer.getCurrentSpan();
       	
	       log.info("Sending message...");
	       log.info("span..."+span.getTraceId());
     
			String spanTraceId= Span.idToHex(span.getTraceId());
			String spanId= Span.idToHex(span.getSpanId());

        MessageProperties properties = new MessageProperties();
        properties.setHeader("spanTraceId", spanTraceId);
      	properties.setHeader("spanId", spanId);
        properties.setContentType(MessageProperties.CONTENT_TYPE_JSON);
        Message message = new Message("1234567;Branch A;SALES;3000.50;Pending approval".getBytes(), properties);

        rabbitTemplate.convertAndSend("spring-boot", message);

    	log.info("rabbitTemplate sent");

        kafkaTemplate.send("my-topic-mk", "1234567;KAFKA Branch A;SALES;3000.50;Pending approval");

    	log.info("kafkaTemplate sent");
*/
        
    	log.info("IN welcomeVIEW executed");
	    log.info("id="+id);


		long startTime = System.nanoTime();

    	
//   	gateway.send("12345678901qaz2wsx3edc4rfv");
    	

    	//----------------------------------- 1.5.10 -----------------------------------
//    	Span span=tracer.getCurrentSpan();
//		String spanTraceId= Span.idToHex(span.getTraceId());
		//----------------------------------- 1.5.10 -----------------------------------


//		String spanId= Span.idToHex(span.getSpanId());
//		String parentId= Span.idToHex(span.getParents().get(0).longValue());


    	//----------------------------------- 2.0.1 -----------------------------------		
		Span span=tracer.currentSpan();
		String spanTraceId= span.context().traceIdString();
		//----------------------------------- 2.0.1 -----------------------------------		


//		String spanId= String.valueOf(span.context().spanId());

		
    	//model.addAttribute("traceid",spanTraceId);
    	model.addAttribute("traceid",id);
    	//model.addAttribute("spanid",UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE);
    	model.addAttribute("spanid",(UUID.randomUUID().toString()).replace("-","").substring(0, 16));
    	
//    	model.addAttribute("app_ajax_url",ajax_url);
//    	model.addAttribute("id",id);
    	
    	log.info("Log data: "+spanTraceId+" "+spanTraceId);
	 
    	 //    	 workUnitGateway.generate(MessageBuilder.withPayload(sampleWorkUnit).build());
//    	source.output().send(MessageBuilder.withPayload(sampleWorkUnit ).build());  	 
//    	 source.output().send(sampleWorkUnit);
    	 
    	
    	log.info("Message sent");
    
//    	User  user  = restTemplate.getForObject("http://localhost:9191/api/get-by-email?email=x@x.com", User.class);
    	
       	System.out.println("END");
    	long duration = System.nanoTime() - startTime;
    			
        return "welcome";
    }



    
/*
	@RequestMapping("/")
	public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);
		return "greeting";
	}
*/
/*	
	// inject via application.properties
		@Value("${welcome.message:test}")
		private String message = "Hello World";


	@RequestMapping("/x")
	public String welcome(Map<String, Object> model) {
		model.put("message", this.message);
		return "greeting";
	}
*/
}