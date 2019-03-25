package acu.project1.business.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acu.project1.business.service.SpecializationService;
import acu.project1.business.transfer.CourseData;
import acu.project1.business.transfer.GradeData;
import acu.project1.business.transfer.GroupaData;
import acu.project1.business.transfer.SpecializationData;
import acu.project1.business.transfer.StudentData;
import acu.project1.persistence.entities.Course;
import acu.project1.persistence.entities.Grade;
import acu.project1.persistence.entities.Groupa;
import acu.project1.persistence.entities.Specialization;
import acu.project1.persistence.entities.Student;
import acu.project1.persistence.entities.Teacher;
import acu.project1.persistence.repositories.GroupaRepository;
import acu.project1.persistence.repositories.SpecializationRepository;
import acu.project1.persistence.repositories.StudentRepository;
import acu.project1.persistence.repositories.TeacherRepository;

@Service
public class SpecializationLogic implements SpecializationService {
	@Autowired
	TeacherRepository teacherRepository;
	@Autowired
	GroupaRepository groupaRepository;
	@Autowired
	SpecializationRepository specializationRepository;
	@Autowired
	StudentRepository studentRepository;
	@Override
	public void removeSpecialization(long id) {
		specializationRepository.delete(id);	
	}
	@Override
	public void addSpecialization(String name) {
		Specialization specialization = new Specialization();
		specialization.setName(name);
		specializationRepository.save(specialization);
	}
	@Override
	public List<SpecializationData> getAllSpecializations() {
		List<Specialization> specializations = specializationRepository.findAll();
		List<SpecializationData> toReturn = new ArrayList<>();
		for(Specialization spec : specializations) {
			List<Groupa> groupas = spec.getGroupas();
			List<String> toReturn2 = new ArrayList<>();
			for(Groupa crs : groupas) {
				List<String> studentsS = new ArrayList<>();
				for(Student stud : crs.getStudents()) {
					studentsS.add(stud.getName());
				}
				//toReturn2.add(new GroupaData(crs.getId(), crs.getNumber(),crs.getSpecialization().getName(), studentsS));
				toReturn2.add(crs.getNumber());
			}
				toReturn.add(new SpecializationData(spec.getId(), spec.getName(), toReturn2));
				
			}
		return toReturn;
	}
	@Override
	public void addGroupaToSpecialization(long groupaId, long specializationId) {
		Groupa groupa = groupaRepository.findOne(groupaId);
		Specialization specialization = specializationRepository.findOne(specializationId);
		specialization.addGroupa(groupa);
		groupa.setSpecialization(specialization);
		groupaRepository.save(groupa);
		
	}
	@Override
	public List<GroupaData> getAllSpecializationGroupas(long id) {
		List<Specialization> specializations = specializationRepository.findAll();
		List<SpecializationData> toReturn = new ArrayList<>();
		for(Specialization spec : specializations) {
			List<Groupa> groupas = spec.getGroupas();
			List<String> toReturn2 = new ArrayList<>();
			for(Groupa crs : groupas) {
				List<String> studentsS = new ArrayList<>();
				for(Student stud : crs.getStudents()) {
					studentsS.add(stud.getName());
				}
				//toReturn2.add(new GroupaData(crs.getId(), crs.getNumber(),crs.getSpecialization().getName(), studentsS));
				toReturn2.add(crs.getNumber());
			}
				toReturn.add(new SpecializationData(spec.getId(), spec.getName(), toReturn2));
				
			}
		List<GroupaData> toReturnn = new ArrayList<>();
		for(Specialization spec : specializations) {
			for(Groupa gr: spec.getGroupas()) {
				if(gr.getSpecialization() == specializationRepository.findOne(id) ) {
					toReturnn.add(new GroupaData(gr.getId(), gr.getNumber(), gr.getSpecialization().getName(), null));
				}
					
			}
		}
		return toReturnn;
	}


}
