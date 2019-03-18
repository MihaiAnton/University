function p = lab2p2(n)
    clf;
    hold on;
    reached = 0;
    for i = 1:n
        s = sim(6, 0.5);
        reached = reached + s;
    end
    p = reached/n;

end


function p =  sim(n, p)
    i = 0;
    j = 0;
    reached = 0;
    hold on;
    axis([0,10,0,10]);
    for k = 1:n
        dir = Bernn(p);
        lin = i + (1-dir);
        col = j + dir;
       
        
        i = lin;
        j = col;
          plot(i,j,'o');
        if i == 2 && j == 4
           reached = 1;            
        end
        
    end
    plot(i,j,'s');

    p = reached;
end


