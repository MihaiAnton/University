function p = p1iii(n)
    
    correct = 0;
    clf;
    hold on;
    
    for i = 1:n
        x = rand;
        y = rand;
        
        if (pdist([x,y;0,1/2]) <= 0.5) && (pdist([x,y;1/2,0])<= 0.5) ||...
           (pdist([x,y;1/2,0]) <= 0.5) && (pdist([x,y;1,1/2])<= 0.5) ||...
          ( pdist([x,y;1,1/2]) <= 0.5) && (pdist([x,y;1/2,1])<= 0.5) ||...
           (pdist([x,y;0,1/2]) <= 0.5) && (pdist([x,y;1/2,1])<= 0.5)
                correct = correct+1;
                plot(x,y,'*b');
        end
        
    end

    p = correct/n;


end