package acuraulm.ThoughtsFinder;

import java.util.Date;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import acuraulm.ThoughtsFinder.business.service.AppuserService;
import acuraulm.ThoughtsFinder.business.service.CommentService;
import acuraulm.ThoughtsFinder.business.service.DataTransferService;
import acuraulm.ThoughtsFinder.business.service.SectionService;
import acuraulm.ThoughtsFinder.business.service.ThoughtService;
import acuraulm.ThoughtsFinder.business.transfer.AppuserDTO;
import acuraulm.ThoughtsFinder.business.transfer.CommentDTO;
import acuraulm.ThoughtsFinder.business.transfer.SectionDTO;
import acuraulm.ThoughtsFinder.business.transfer.ThoughtDTO;
import acuraulm.ThoughtsFinder.persistence.repositories.AppuserRepository;
import acuraulm.ThoughtsFinder.persistence.repositories.CommentRepository;
import acuraulm.ThoughtsFinder.persistence.repositories.SectionRepository;
import acuraulm.ThoughtsFinder.persistence.repositories.ThoughtRepository;

@Component
public class TestingComponent implements CommandLineRunner{

	@Autowired
	AppuserRepository appuserRepository;
	
	@Autowired
	AppuserService appuserService;
	
	@Autowired
	ThoughtService thoughtService;
	@Autowired
	CommentService commentService;
	@Autowired
	SectionService sectionService;
	
	@Autowired
	ThoughtRepository thoughtRepository;
	
	@Autowired
	CommentRepository commentRepository;
	
	@Autowired
	SectionRepository sectionRepository;
	
	@Autowired
	DataTransferService dataTransferService;
	
	
	@Override
	
    public void run(String... strings) throws Exception {
	
		//create10appusers();
		//printAppusers();
	
		
		//create10sections();
		//printSections();
	
	//	share5thoughts((long) 1);
	//	printThoughts((long) 1);
		
	//	share5thoughts((long) 2);
	//	printThoughts((long) 2);
    }	
	
	void printAppusers() {	
		Set<AppuserDTO> appusers = appuserService.findAllAppusers();
		for(AppuserDTO appuserDTO:appusers)
			System.out.println(appuserDTO.getId() + ", " + appuserDTO.getName());	
	}
	void printThoughts(long appuserId) {
		Set<ThoughtDTO> thoughts = thoughtService.findAllThoughtsByAppuserId(appuserId);
		for(ThoughtDTO thoughtDTO:thoughts)
			System.out.println(thoughtDTO.getId() + ", " + thoughtDTO.getContent());	
	}
	void printSections() {
		Set<SectionDTO> sections = sectionService.findAllSections();
		for(SectionDTO sectionDTO:sections) 
			System.out.println(sectionDTO.getId() + ", " + sectionDTO.getName());
	}
	void printComments(long thoughtId) {
		Set<CommentDTO> comments = commentService.findAllCommentsByThoughtId(thoughtId);
		//Set<CommentDTO> comments = commentService.findAllComments();
		for(CommentDTO commentDTO:comments)
			System.out.println(commentDTO.getId() + ", " + commentDTO.getContent());
	}
	
	void create10appusers() {
		AppuserDTO appuserDTO1 = new AppuserDTO();
		appuserDTO1.setName("Acu Raul-Mihai");
		appuserDTO1.setUsername("acuraulm@gmail.com");
		appuserDTO1.setPassword("acuraulm");
		appuserService.createAppuser(appuserDTO1);
		
		AppuserDTO appuserDTO2 = new AppuserDTO();
		appuserDTO2.setName("Acs David");
		appuserDTO2.setUsername("acsdavid@gmail.com");
		appuserDTO2.setPassword("acsdavid");
		appuserService.createAppuser(appuserDTO2);
		
		AppuserDTO appuserDTO3 = new AppuserDTO();
		appuserDTO3.setName("Biris Alexandra");
		appuserDTO3.setUsername("birisalexandra@gmail.com");
		appuserDTO3.setPassword("birisalexandra");
		appuserService.createAppuser(appuserDTO3);
		
		AppuserDTO appuserDTO4 = new AppuserDTO();
		appuserDTO4.setName("Gog Andrei");
		appuserDTO4.setUsername("gogandrei@gmail.com");
		appuserDTO4.setPassword("gogandrei");
		appuserService.createAppuser(appuserDTO4);
		
		AppuserDTO appuserDTO5 = new AppuserDTO();
		appuserDTO5.setName("Borsa Laurentiu");
		appuserDTO5.setUsername("borsalaurentiu@gmail.com");
		appuserDTO5.setPassword("borsalaurentiu");
		appuserService.createAppuser(appuserDTO5);
		
		AppuserDTO appuserDTO6 = new AppuserDTO();
		appuserDTO6.setName("Sirbu Natalia Carina");
		appuserDTO6.setUsername("sirbunataliacarina@gmail.com");
		appuserDTO6.setPassword("sirbunataliacarina");
		appuserService.createAppuser(appuserDTO6);
		
		AppuserDTO appuserDTO7 = new AppuserDTO();
		appuserDTO7.setName("Matei Maria");
		appuserDTO7.setUsername("mateimaria@gmail.com");
		appuserDTO7.setPassword("mateimaria");
		appuserService.createAppuser(appuserDTO7);
		
		AppuserDTO appuserDTO8 = new AppuserDTO();
		appuserDTO8.setName("Poenar Ionut Paul");
		appuserDTO8.setUsername("peonarionutpaul@gmail.com");
		appuserDTO8.setPassword("poenarionutpaul");
		appuserService.createAppuser(appuserDTO8);
		
		AppuserDTO appuserDTO9 = new AppuserDTO();
		appuserDTO9.setName("Miculas Roland");
		appuserDTO9.setUsername("miculasroland@gmail.com");
		appuserDTO9.setPassword("miculasroland");
		appuserService.createAppuser(appuserDTO9);
		
		AppuserDTO appuserDTO10 = new AppuserDTO();
		appuserDTO10.setName("Tanasa Laurentiu");
		appuserDTO10.setUsername("tanasalaurentiu@gmail.com");
		appuserDTO10.setPassword("tanasalaurentiu");
		appuserService.createAppuser(appuserDTO10);
		
	}
	
	void create10sections() {
		SectionDTO sectionDTO1 = new SectionDTO();
		sectionDTO1.setName("Humour");
		sectionService.createSection(sectionDTO1);
		
		SectionDTO sectionDTO2 = new SectionDTO();
		sectionDTO2.setName("Personal");
		sectionService.createSection(sectionDTO2);
		
		SectionDTO sectionDTO3 = new SectionDTO();
		sectionDTO3.setName("Academy");
		sectionService.createSection(sectionDTO3);
		
		SectionDTO sectionDTO4 = new SectionDTO();
		sectionDTO4.setName("Technology");
		sectionService.createSection(sectionDTO4);
		
		SectionDTO sectionDTO5 = new SectionDTO();
		sectionDTO5.setName("Economy");
		sectionService.createSection(sectionDTO5);
		
		SectionDTO sectionDTO6 = new SectionDTO();
		sectionDTO6.setName("Politics");
		sectionService.createSection(sectionDTO6);
		
		SectionDTO sectionDTO7 = new SectionDTO();
		sectionDTO7.setName("Movies");
		sectionService.createSection(sectionDTO7);
		
		SectionDTO sectionDTO8 = new SectionDTO();
		sectionDTO8.setName("Entertainment");
		sectionService.createSection(sectionDTO8);
		
		SectionDTO sectionDTO9 = new SectionDTO();
		sectionDTO9.setName("WTF");
		sectionService.createSection(sectionDTO9);
		
		SectionDTO sectionDTO10 = new SectionDTO();
		sectionDTO10.setName("NSFW");
		sectionService.createSection(sectionDTO10);
		
	}
	
	void share5thoughts(long appuserId) {
		ThoughtDTO thoughtDTO1 = new ThoughtDTO();
		thoughtDTO1.setContent("Cel mai humourous thought al " + appuserId);
		thoughtDTO1.setDate(new Date());
		thoughtDTO1.setAppuserId(appuserId);
		thoughtDTO1.getSectionsIds().add((long) 11);
		thoughtService.shareThought(thoughtDTO1);
		
		ThoughtDTO thoughtDTO2 = new ThoughtDTO();
		thoughtDTO2.setContent("Cel mai academic thought al " + appuserId);
		thoughtDTO2.setDate(new Date());
		thoughtDTO2.setAppuserId(appuserId);
		thoughtDTO2.getSectionsIds().add((long) 13);
		thoughtService.shareThought(thoughtDTO2);
		
		ThoughtDTO thoughtDTO3 = new ThoughtDTO();
		thoughtDTO3.setContent("Cel mai politic thought al " + appuserId);
		thoughtDTO3.setDate(new Date());
		thoughtDTO3.setAppuserId(appuserId);
		thoughtDTO3.getSectionsIds().add((long) 16);
		thoughtService.shareThought(thoughtDTO3);
		
		ThoughtDTO thoughtDTO4 = new ThoughtDTO();
		thoughtDTO4.setContent("Cel mai WTF thought al " + appuserId);
		thoughtDTO4.setDate(new Date());
		thoughtDTO4.setAppuserId(appuserId);
		thoughtDTO4.getSectionsIds().add((long) 19);
		thoughtService.shareThought(thoughtDTO4);
		
		ThoughtDTO thoughtDTO5 = new ThoughtDTO();
		thoughtDTO5.setContent("Cel mai economic thought al " + appuserId);
		thoughtDTO5.setDate(new Date());
		thoughtDTO5.setAppuserId(appuserId);
		thoughtDTO5.getSectionsIds().add((long) 15);
		thoughtService.shareThought(thoughtDTO5);
		
	}
	void test2() {
		AppuserDTO appuserDTO1 = new AppuserDTO();
		appuserDTO1.setName("Acu Raul-Mihai");
		appuserDTO1.setUsername("acuraulm@gmail.com");
		appuserDTO1.setPassword("acuraulm");
		appuserService.createAppuser(appuserDTO1);
		
		SectionDTO sectionDTO1 = new SectionDTO();
		sectionDTO1.setName("Fun");
		sectionService.createSection(sectionDTO1);
		SectionDTO sectionDTO2 = new SectionDTO();
		sectionDTO2.setName("Personal");
		sectionService.createSection(sectionDTO2);
		SectionDTO sectionDTO3 = new SectionDTO();
		sectionDTO3.setName("Merchandise");
		sectionService.createSection(sectionDTO3);
		
		/*
		ThoughtDTO thoughtDTO1 = new ThoughtDTO();
		thoughtDTO1.setContent("Am scris asta din intamplare");
		thoughtDTO1.setDate(new Date());
		thoughtDTO1.setAppuserId((long) 1);
		thoughtDTO1.getSectionsIds().add((long) 2);
		thoughtService.shareThought(thoughtDTO1);
		
		CommentDTO commentDTO1 = new CommentDTO();
		commentDTO1.setContent("Imi place comentariul asta");
		commentDTO1.setDate(new Date());
		commentDTO1.setAppuserId((long) 1);
		commentDTO1.setThoughtId((long) 3);
		commentService.postComment(commentDTO1);
		
		CommentDTO commentDTO2 = new CommentDTO();
		commentDTO2.setContent("Chiar imi place comentariul asta");
		commentDTO2.setDate(new Date());
		commentDTO2.setAppuserId((long) 1);
		commentDTO2.setThoughtId((long) 3);
		commentService.postComment(commentDTO2);*/
	}
	
	void test1() {
		System.out.print("\nCreating appusers\n");
		for(int a=0; a<10; a++) {
			System.out.print('.');
			AppuserDTO appuserDTO = new AppuserDTO();
			appuserDTO.setName("This is appuser" + a);
			appuserDTO.setUsername("appuser" + a + "@gmail.com");
			appuserDTO.setPassword("appuser" + a);
			appuserService.createAppuser(appuserDTO);
			if(a==9) System.out.print("done.\n");
		}
		/*
		System.out.println("Initializing database:");
		System.out.print("\nCreating sections");
		for(int c=0; c<10; c++) {
			System.out.print('.');
			Section section = new Section();
			section.setName("Section" + c);
			sectionRepository.save(section);
			if(c==9) System.out.print("done.");
		}
		System.out.print("\nCreating appusers");
		for(int a=0; a<10; a++) {
			System.out.print('.');
			Appuser appuser = new Appuser();
			appuser.setName("This is appuser" + a);
			appuser.setUsername("appuser" + a + "@gmail.com");
			appuser.setPassword("appuser" + a);
			appuserRepository.save(appuser);
			if(a==9) System.out.print("done.\n");
		}
		System.out.print("\nSharing thought#1");
		try {
			Thought thought = new Thought();
			Appuser appuser = appuserRepository.getOne((long) 11);
			thought.setAppuser(appuser);
			thought.setDate(new Date());
			thought.setContent("This is shared thought #1 by appuser1");
			
			appuser.getSharedThoughts().add(thought);
			
			thoughtRepository.save(thought);
			
			System.out.print("..........done.\n");
		}
		catch(Exception ex) {
			ex.printStackTrace();
			System.out.print("..........failed.\n");
		}
		
		System.out.println(appuserRepository.findAll());
		System.out.println(thoughtRepository.findAll().toString());
		System.out.println("Shared thoughts of appuser11:" + appuserRepository.getOne((long) 11).getSharedThoughts().toString());
		
		System.out.println(dataTransferService.getAppuserDTO(appuserRepository.getOne((long) 11)).getSharedThoughtsIds());
		*/
		System.out.println(appuserService.findAllAppusers().size());
		appuserService.deleteAppuserById((long)5);
		System.out.println(appuserService.findAllAppusers().size());
		System.out.println(appuserService.verifyLogin("appuser1@gmail.com","appuser2"));
	
	}
}
