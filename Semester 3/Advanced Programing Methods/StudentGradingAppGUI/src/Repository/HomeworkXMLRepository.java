package Repository;

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

public class HomeworkXMLRepository extends MemoryRepository<Integer, Homework> {

    private static DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

    public HomeworkXMLRepository(Validator<Homework> validator, String fileName) {
        super(validator, fileName);
    }

    @Override
    public void readFromFile() {
        try{

            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(new File(getFileName()));

            Element element = document.getDocumentElement();

            NodeList nodeList = element.getElementsByTagName("homework");
            for(int i=0;i<nodeList.getLength();i++){

                Element item = (Element)nodeList.item(i);

                Homework homework = getHomeworkFromElement(item, document);

                save(homework);

            }


        }
        catch(Exception e){}
    }

    private static Homework getHomeworkFromElement(Element item, Document document) {

        int id = Integer.parseInt(item.getAttribute("id"));

        String description = item.getElementsByTagName("description").item(0).getChildNodes().item(0).getNodeValue();

        int targetWeek = Integer.parseInt(item.getElementsByTagName("targetWeek").item(0).getChildNodes().item(0).getNodeValue());

        int deadlineWeek = Integer.parseInt(item.getElementsByTagName("deadlineWeek").item(0).getChildNodes().item(0).getNodeValue());

        return new Homework(id,description,targetWeek,deadlineWeek,targetWeek);

    }


    @Override
    public void writeToFile() {
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            Element root = document.createElement("homeworkList");

            for (Homework homework : findAll()) {

                root.appendChild(getHomeworkElement(homework, document));
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

    private static Node getHomeworkElement(Homework homework, Document document) {

        Element homeworkRoot = document.createElement("homework");

        homeworkRoot.setAttribute("id",""+homework.getId());
        homeworkRoot.appendChild(getLabelNode("description",homework.getDescription(),document));
        homeworkRoot.appendChild(getLabelNode("targetWeek",""+homework.getTargetWeek(),document));
        homeworkRoot.appendChild(getLabelNode("deadlineWeek",""+homework.getDeadlineWeek(),document));

        return homeworkRoot;
    }

    private static Node getLabelNode(String label, String value, Document document){

        Node labelNode = document.createElement(label);
        labelNode.appendChild(document.createTextNode(value));
        return labelNode;

    }
}

















































