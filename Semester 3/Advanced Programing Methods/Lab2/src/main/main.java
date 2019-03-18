package main;















import java.util.ArrayList;
import java.time.LocalDateTime;



public class main {
    public static void main(String[] args) {

        MessageTaskTester tester = new MessageTaskTester();
        tester.test();

        StrategyTaskRunnerTest tester2 = new StrategyTaskRunnerTest();
        tester2.test(Strategy.valueOf(args[0]));

        AbstractTaskRunnerTest tester3 = new AbstractTaskRunnerTest();
        tester3.test(Strategy.valueOf(args[0]));
    }
}


////////////////////////////////// 1
abstract class Task {

    private String taskId;
    private String description;

    public Task(String tId, String description){
        taskId = tId;
        this.description = description;
    }

    public String getTaskId() {
        return this.taskId;
    }

    public String getDescription(){

        return this.description;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    public void setTaskId(String taskId){

        this.taskId = taskId;
    }

    public abstract void execute();

    public String toString(){
        return "Id: " + getTaskId() + "|desription=" + getDescription();
    }

}


////////////////////////////////// 2
class MessageTask extends Task{

    String from, to, message;
    LocalDateTime time;

    public MessageTask(String tId, String description, String message, String from, String to, LocalDateTime time){
        super(tId, description);
        this.from = from;
        this.to = to;
        this.time = time;
        this.message = message;
    }

    @Override
    public void execute() {

        System.out.println(this.message + this.time);
    }

    @Override
    public String toString(){
        return super.toString() + "|message=" + this.message + "|from=" + this.from + "|to=" + this.to + "|date=" + this.time;
    }

}

//////////////Sorting classes

////////////////////////////////// 3
abstract class AbstractSorter{



    public AbstractSorter(){ }

    public abstract int[] sort(int[] arr);
}

class BubbleSort extends AbstractSorter{

    public BubbleSort(){ }

    @Override
    public int[] sort(int[] arr){

        boolean sorted = false;
        int startPos = 0;

        while(!sorted && startPos< arr.length - 1){
            int i = 0;
            sorted = true;

            while(i+1<arr.length - startPos && arr[i+1] < arr[i]){
                int aux = arr[i];
                arr[i] = arr[i+1];
                arr[i+1] = aux;
                i++;
                sorted = false;
            }
            startPos++;
        }

        return arr;
    }
}

class QuickSort extends AbstractSorter{

    @Override
    public int[] sort(int[] arr){
        qSort(arr,0, arr.length);
        return arr;
    }

    private void qSort(int[] arr, int left, int right){
        //sorts arr in interval [i,j)
        int i = left;
        int j = right-1;

        if(i<j) {
            while (i < j) {
                if (arr[i] <= arr[j]) {
                    i++;
                } else {
                    int aux = arr[i];
                    arr[i] = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = aux;
                    j--;
                }
            }
            qSort(arr, left, j);
            qSort(arr, j, right);
        }
    }
}






class SortingTask extends Task{

    int[] arr;
    AbstractSorter sorter;

    public SortingTask(String tId, String description, int[] arr, String strategy){
        super(tId, description);
        this.arr = arr;

        if(strategy.equals("bubble")){
            sorter = new BubbleSort();
        }
        else if(strategy.equals("quick")){
            sorter = new QuickSort();
        }
    }

    @Override
    public void execute(){
        int[] sorted = sorter.sort(arr);
        for(int elem : sorted){
            System.out.println(elem + " ");
        }
    }
}


////////////////////////////////// 4
class MessageTaskTester{

    Task[] arr;

    public MessageTaskTester(){
        arr = new Task[5];
    }

    public void test(){
        arr[0] = new MessageTask("1","Desc1","Mesaj1","sender1","dest1",LocalDateTime.now());
        arr[1] = new MessageTask("2","Desc2","Mesaj2","sender2","dest2",LocalDateTime.now());
        arr[2] = new MessageTask("3","Desc3","Mesaj3","sender3","dest3",LocalDateTime.now());
        arr[3] = new MessageTask("4","Desc4","Mesaj4","sender4","dest4",LocalDateTime.now());
        arr[4] = new MessageTask("5","Desc5","Mesaj5","sender5","dest5",LocalDateTime.now());

        for(Task t:arr){
            System.out.println(t.toString());;
        }
    }
}

////////////////////////////////// 5
interface Container{
    Task remove();
    void add(Task t);
    int size();
    boolean isEmpty();
}

abstract class GenericContainer implements Container{

    protected ArrayList<Task> arr;
    //protected Task[] arr;
    protected int elemCount;

    public GenericContainer(){
        elemCount = 0;
        arr = new ArrayList<Task>();
    }

    @Override
    public int size(){
        return arr.size();
    }

    @Override
    public void add(Task t){
        //resize();
        //arr[this.size()] = t;
        arr.add(t);
       // this.elemCount++;
    }

    @Override
    public boolean isEmpty(){
        return size() == 0;
    }
}


class StackContainer extends GenericContainer{


    public StackContainer(){
        super();
    }



    @Override
    public Task remove(){
        if(!isEmpty()) {



            Task copy = arr.get(arr.size() - 1);
            arr.remove(arr.size() - 1);
            //resize();
            return copy;
        }
        //resize();
        return null;
    }
}

class QueueContainer extends GenericContainer{

    public QueueContainer(){
        super();
    }





    @Override
    public Task remove(){
        if(!isEmpty()){

            Task copy = arr.get(0);


            arr.remove(0);
            return copy;
        }

        return null;
    }

}

////////////////////////////////// 6,7

enum Strategy{
    LIFO, FIFO;
}

interface Factory {
    Container getContainer(Strategy strategy);
}

class TaskContainerFactory implements Factory{

    private TaskContainerFactory() {}

    private Container container;
    private static TaskContainerFactory instance;



    public static TaskContainerFactory getInstance(){
        if(instance == null){
            instance = new TaskContainerFactory();
        }
        return instance;
    }

    public Container getContainer(Strategy strategy){
        if(container == null) {

            if (strategy == Strategy.LIFO) {
               container =  new StackContainer();
            } else if (strategy == Strategy.FIFO) {
                container = new QueueContainer();
            }
        }

        return container;
    }
}

////////////////////////////////// 8

interface TaskRunner{
    void executeOneTask();
    void executeAll();
    void addTask(Task t);
    boolean hasTask();
}

////////////////////////////////// 9

class StrategyTaskRunner implements TaskRunner{

    private Container container;

    public StrategyTaskRunner(Strategy strategy){
        TaskContainerFactory factory = TaskContainerFactory.getInstance();
        container = factory.getContainer(strategy);
    }

    @Override
    public void executeOneTask(){
        if(hasTask()) {
            Task t = container.remove();
            t.execute();
        }
    }

    @Override
    public void executeAll(){
        while(hasTask()){
            executeOneTask();
        }
    }

    @Override
    public void addTask(Task t){
        container.add(t);
    }

    @Override
    public boolean hasTask(){
        return !container.isEmpty();
    }

}

////////////////////////////////// 10

class StrategyTaskRunnerTest{

    public void test(Strategy strategy) {
        StrategyTaskRunner taskRunner = new StrategyTaskRunner(strategy);

        taskRunner.addTask(new MessageTask("1", "Desc1", "Mesaj1", "sender1", "dest1", LocalDateTime.now()));
        taskRunner.addTask(new MessageTask("2", "Desc2", "Mesaj2", "sender2", "dest2", LocalDateTime.now()));
        taskRunner.addTask(new MessageTask("3", "Desc3", "Mesaj3", "sender3", "dest3", LocalDateTime.now()));
        taskRunner.addTask(new MessageTask("4", "Desc4", "Mesaj4", "sender4", "dest4", LocalDateTime.now()));
        taskRunner.addTask(new MessageTask("5", "Desc5", "Mesaj5", "sender5", "dest5", LocalDateTime.now()));

        taskRunner.executeAll();
    }
}


////////////////////////////////// 11

abstract class AbstractTaskRunner implements TaskRunner{

    protected TaskRunner taskRunner;

    public AbstractTaskRunner(TaskRunner taskRunner){
        this.taskRunner = taskRunner;
    }
    @Override
    public void addTask(Task t){
        taskRunner.addTask(t);
    }

    @Override
    public boolean hasTask(){
        return taskRunner.hasTask();
    }


}


////////////////////////////////// 12

class PrinterTaskRunner extends AbstractTaskRunner{

    public PrinterTaskRunner(TaskRunner taskRunner){
        super(taskRunner);
    }



    @Override
    public void executeOneTask(){
        taskRunner.executeOneTask();
        System.out.println(LocalDateTime.now().getHour() + ":" +  LocalDateTime.now().getMinute() +"\n");
    }

    @Override
    public void executeAll(){
        while(taskRunner.hasTask())
            this.executeOneTask();
    }



}

class DelayTaskRunner extends AbstractTaskRunner{

    public DelayTaskRunner(TaskRunner taskRunner){
        super(taskRunner);
    }


    @Override
    public void executeOneTask(){
        try{
            Thread.sleep(2000);
        }
        catch(InterruptedException e){;}


        taskRunner.executeOneTask();

    }

    @Override
    public void executeAll(){
        while(taskRunner.hasTask())
            this.executeOneTask();
    }

}

////////////////////////////////// 13, 14

class AbstractTaskRunnerTest{

    public void test(Strategy strategy){
        Task[] arr = new Task[5];

        arr[0] = new MessageTask("1","Desc1","Mesaj1","sender1","dest1",LocalDateTime.now());
        arr[1] = new MessageTask("2","Desc2","Mesaj2","sender2","dest2",LocalDateTime.now());
        arr[2] = new MessageTask("3","Desc3","Mesaj3","sender3","dest3",LocalDateTime.now());
        arr[3] = new MessageTask("4","Desc4","Mesaj4","sender4","dest4",LocalDateTime.now());
        arr[4] = new MessageTask("5","Desc5","Mesaj5","sender5","dest5",LocalDateTime.now());


        TaskRunner runner1 = new StrategyTaskRunner(strategy);
        for(int i=0;i<arr.length;i++){
            runner1.addTask(arr[i]);
        }
        runner1.executeAll();
        System.out.println("\n");

        arr[0] = new MessageTask("1","Desc1","Mesaj1","sender1","dest1",LocalDateTime.now());
        arr[1] = new MessageTask("2","Desc2","Mesaj2","sender2","dest2",LocalDateTime.now());
        arr[2] = new MessageTask("3","Desc3","Mesaj3","sender3","dest3",LocalDateTime.now());
        arr[3] = new MessageTask("4","Desc4","Mesaj4","sender4","dest4",LocalDateTime.now());
        arr[4] = new MessageTask("5","Desc5","Mesaj5","sender5","dest5",LocalDateTime.now());

        TaskRunner runner2 = new PrinterTaskRunner(runner1);
        for(int i=0;i<arr.length;i++){
            runner1.addTask(arr[i]);
        }
        runner2.executeAll();
        System.out.println("\n");
        arr[0] = new MessageTask("1","Desc1","Mesaj1","sender1","dest1",LocalDateTime.now());
        arr[1] = new MessageTask("2","Desc2","Mesaj2","sender2","dest2",LocalDateTime.now());
        arr[2] = new MessageTask("3","Desc3","Mesaj3","sender3","dest3",LocalDateTime.now());
        arr[3] = new MessageTask("4","Desc4","Mesaj4","sender4","dest4",LocalDateTime.now());
        arr[4] = new MessageTask("5","Desc5","Mesaj5","sender5","dest5",LocalDateTime.now());

        TaskRunner runner3 = new DelayTaskRunner(runner1);
        for(int i=0;i<arr.length;i++){
            runner1.addTask(arr[i]);
        }
        runner3.executeAll();
        System.out.println("\n");
    }
}
































































