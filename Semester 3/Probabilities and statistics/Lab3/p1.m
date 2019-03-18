function p = p1(n)
    
    clf;
    hold on;
    axis([0,1,0,1]);

    center = 0;
    corner = 0;



    for i = 1:n
        x = rand;
        y = rand;
        dC = pdist([x,y;1/2,1/2]);
        d1 = pdist([0,0;x,y]);
        d2 = pdist([0,1;x,y]);
        d3 = pdist([1,0;x,y]);
        d4 = pdist([1,1;x,y]);
        
        if dC < d1 && dC < d2 &&dC < d3 &&dC < d4
            center = center + 1;
            plot(x,y,'b*');
        else
            corner = corner + 1;
        end
    end
    
    p = center/n;

end