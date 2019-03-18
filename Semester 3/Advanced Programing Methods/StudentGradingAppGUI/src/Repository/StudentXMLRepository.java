package Repository;

import Domain.Student;
import Validators.Validator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class StudentXMLRepository extends MemoryRepository<String, Student> {

    private static DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

    public StudentXMLRepository(Validator<Student> validator, String fileName) {
        super(validator, fileName);
    }

    @Override
    public void readFromFile() {
        if(documentBuilderFactory == null){
            documentBuilderFactory = DocumentBuilderFactory.newInstance();
        }

        try{
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(new File(getFileName()));

            Element element = document.getDocumentElement();

            NodeList nodeList = element.getElementsByTagName("student");

            for(int i=0;i<nodeList.getLength();i++) {

                Element item = (Element) nodeList.item(i);

                Student student = getStudentFromElement(item);

                save(student);
            }

        }
        catch(Exception e){}
    }

    private static Student getStudentFromElement(Element element){

        String id = element.getAttribute("id");
        NodeList nodeList;

        nodeList = element.getElementsByTagName("name");
        String name = nodeList.item(0).getChildNodes().item(0).getNodeValue();

        nodeList = element.getElementsByTagName("group");
        int group = Integer.parseInt(nodeList.item(0).getChildNodes().item(0).getNodeValue());

        nodeList = element.getElementsByTagName("email");
        String email = nodeList.item(0).getChildNodes().item(0).getNodeValue();

        nodeList = element.getElementsByTagName("teacher");
        String teacher = nodeList.item(0).getChildNodes().item(0).getNodeValue();

        return new Student(id,name,group,email,teacher);

    }



    @Override
    public void writeToFile() {
        if(documentBuilderFactory == null){
            documentBuilderFactory = DocumentBuilderFactory.newInstance();
        }


        try{
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            Element root = document.createElement("students");

            for (Student student: findAll()) {
                root.appendChild(getStudentElement(student, document));
            }
            document.appendChild(root);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(getFileName()));

            transformer.transform(domSource,streamResult);


        }
        catch(Exception e){}

    }




    private static Element getStudentElement(Student student,Document document){

        //create the root
        Element studentRoot = document.createElement("student");

        //set id attribute
        studentRoot.setAttribute("id",student.getId());

        //add name
        studentRoot.appendChild(getFieldElement("name",student.getName(),document));

        //add group
        studentRoot.appendChild(getFieldElement("group","" + student.getGroup(),document));

        //add email
        studentRoot.appendChild(getFieldElement("email",student.getEmail(),document));

        //add teacher
        studentRoot.appendChild(getFieldElement("teacher",student.getTeacher(),document));

        return studentRoot;
    }

    private static Element getFieldElement(String label,String value,Document document){

        Element field = document.createElement(label);
        field.appendChild(document.createTextNode(value));
        return field;

    }
}




























