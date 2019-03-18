package Service;

import AppSim.Student;
import AppSim.StudentRepo;
import AppSim.StudentTxtRepo;
import Domain.Laptop;
import Domain.LaptopType;
import Domain.Sale;
import Repository.ClientRepository;
import Repository.LaptopRepository;
import Repository.SaleRepository;
import Utils.Observable;
import Utils.Observer;
import Utils.ServiceEvent;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Service implements Observable<ServiceEvent> {

    private ArrayList<Observer> observers;

    private String data;
    private ClientRepository clientRepository;
    private LaptopRepository laptopRepository;
    private SaleRepository saleRepository;

    public Service(ClientRepository clientRepository, LaptopRepository laptopRepository, SaleRepository saleRepository){

        this.observers = new ArrayList<>();
        this.clientRepository = clientRepository;
        this.laptopRepository = laptopRepository;
        this.saleRepository = saleRepository;
    }

    public void addEntity1(Object s){
        this.clientRepository.save((Student)s);
    }

    public void updateEntity1(Object s){
        this.clientRepository.update((Student)s);
    }

    public void deleteEntity1(Object s){
        this.clientRepository.delete(((Student)s).getId());
    }

    public Iterable<Student> getEntity1Iterable(){
        return this.clientRepository.findAll();
    }


    @Override
    public void addObserver(Observer<ServiceEvent> observer) {

        this.observers.add(observer);
    }

    @Override
    public void removeObserver(Observer<ServiceEvent> observer) {
        this.observers.remove(observer);
    }

    @Override
    public void notifyObserver(ServiceEvent event) {
        for (Observer observer: this.observers) {
            observer.notify(event);
        }
    }


    public void printLaptopList() {

        System.out.println("Hello");

        //get producs
        ArrayList<String> prods = new ArrayList<>();

        for (Object l : this.laptopRepository.findAll()) {
            Laptop lp = (Laptop) l;
            boolean has = false;
            for (String s :
                    prods) {
                if (s.equals(lp.getProd()))
                    has = true;
            }

            if (!has) {
                prods.add(lp.getProd());
            }
        }

        List<Laptop> laplist = new ArrayList<>();
        for (Object l : this.laptopRepository.findAll()) {
            Laptop lp = (Laptop) l;
            laplist.add(lp);
        }

        laplist.sort((x, y) -> {

            if (x.getProd().compareTo(y.getProd()) == 0) {
                if (x.getPrice() < y.getPrice())
                    return -1;
                else return 1;
            } else {
                return x.getProd().compareTo(y.getProd());
            }
        });

        for (Laptop l :
                laplist) {
            System.out.println(l);
        }

/*
        for (String prod:prods ) {



            List<Laptop> list = new ArrayList<>();
            for (Object l: this.laptopRepository.findAll()) {
                Laptop lp = (Laptop)l;
                if(lp.getProd().equals(prod)){
                    list.add(lp);
                }
            }

            list.sort((x,y)-> {

                if(x.getProd().compareTo(y.getProd()) == 0) {
                    if (x.getPrice() < y.getPrice())
                        return -1;
                    else return 1;
                }

                else{
                    return x.getProd().compareTo(y.getProd());
                    }
            });

            for (Laptop l:
                 list) {
                System.out.println(l);
            }

        }
*/
        //}

    }

    public void setDateParam(String data){
        this.data = data;
    }

    public void printLaptopData(){

        for(Object o : this.saleRepository.findAll()){

            Sale s = (Sale)o;

            Laptop samplel = new Laptop(s.getLaptopId(),"","",1,"home");
            Laptop l = (Laptop)laptopRepository.findOne(s.getLaptopId());

            if(s.getDate().equals(this.data) && l.getType().matches(".*"+"gaming"+".*")){
                System.out.println(l.getModel() + " " + s.getClientId() + " " + l.getPrice());
            }



        }

    }

    public void printMaxMonth(){

        int maxM = 1;
        float mprofit = 0;


        for(int i=1;i<=12;i++){

            float profit = 0;
            for (Object o:this.saleRepository.findAll()           ) {
                Sale s = (Sale)o;

                String dt = s.getDate();
                java.lang.String[] splits = dt.split("\\-");
                if(Integer.parseInt(splits[1]) == i) {
                    Laptop l = (Laptop)laptopRepository.findOne(s.getLaptopId());
                    if(l.getType().equals("business"))
                        profit = profit + l.getPrice();
                }

            }

            if(profit > profit){
                mprofit = profit;
                maxM = i;
            }

        }

        System.out.println("Luna maxima:" + maxM);

    }

}
