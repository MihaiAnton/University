package Service;

import Utils.Observable;
import Utils.Observer;
import Utils.ServiceEvent;

import java.util.ArrayList;

public class Service implements Observable<ServiceEvent> {

    private ArrayList<Observer> observers;





    public Service(/**params**/){

        this.observers = new ArrayList<>();
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
}
