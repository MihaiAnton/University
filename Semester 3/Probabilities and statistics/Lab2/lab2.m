

function [prob , prob2] = lab2(n)
    hitSmall = 0;
    hitAll = 0;
    clf;
    hold on;
    for i = 1:n
        a = 2*rand;
        b = 2*rand;
        dist = distance(1,1,a,b);
        
        if dist <= 0.5
            hitSmall = hitSmall + 1;
            hitAll = hitAll + 1;
            plot(a,b,'*r');
        elseif dist <= 1
           hitAll = hitAll + 1;
           plot(a,b,'*y');
        else
           plot(a,b,'*b');
        end
           
    end
    prob = hitAll/n;
    prob2 = hitSmall/n;
end
    
function dist = distance(a,b,x,y)
        dist = sqrt((a-x)^2 + (b-y)^2);
end

