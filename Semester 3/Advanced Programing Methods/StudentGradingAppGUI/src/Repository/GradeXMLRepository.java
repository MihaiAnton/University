package Repository;

import Domain.Grade;
import Domain.Homework;
import Validators.Validator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class GradeXMLRepository extends MemoryRepository<Integer, Grade>{


    private static DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

    public GradeXMLRepository(Validator<Grade> validator, String fileName) {
        super(validator, fileName);
    }



    @Override
    public void readFromFile() {
        try{

            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(new File(getFileName()));

            Element element = document.getDocumentElement();

            NodeList nodeList = element.getElementsByTagName("grade");
            for(int i=0;i<nodeList.getLength();i++){

                Element item = (Element)nodeList.item(i);

                Grade grade = getGradeFromElement(item, document);

                save(grade);

            }


        }
        catch(Exception e){}
    }

    private static Grade getGradeFromElement(Element item, Document document) {

        int id = Integer.parseInt(item.getAttribute("id"));

        String sid = item.getElementsByTagName("studentId").item(0).getChildNodes().item(0).getNodeValue();

        String hid = item.getElementsByTagName("homeworkId").item(0).getChildNodes().item(0).getNodeValue();

        double grade = Double.parseDouble(item.getElementsByTagName("gr").item(0).getChildNodes().item(0).getNodeValue());

        return new Grade(sid,Integer.parseInt(hid),id, grade);

    }


    @Override
    public void writeToFile() {
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            Element root = document.createElement("gradeList");

            for (Grade grade : findAll()) {

                root.appendChild(getGradeElement(grade, document));
            }

            document.appendChild(root);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            DOMSource domSource  = new DOMSource(document);
            StreamResult streamResult = new StreamResult(getFileName());

            transformer.transform(domSource,streamResult);

        }
        catch(Exception e){}


    }

    private static Node getGradeElement(Grade grade, Document document) {

        Element homeworkRoot = document.createElement("grade");

        homeworkRoot.setAttribute("id",""+grade.getId());
        homeworkRoot.appendChild(getLabelNode("studentId",grade.getStudId(),document));
        homeworkRoot.appendChild(getLabelNode("homeworkId",""+grade.getHomeworkId(),document));
        homeworkRoot.appendChild(getLabelNode("gr",String.valueOf(grade.getGrade()),document));

        return homeworkRoot;
    }

    private static Node getLabelNode(String label, String value, Document document){

        Node labelNode = document.createElement(label);
        labelNode.appendChild(document.createTextNode(value));
        return labelNode;

    }

}
