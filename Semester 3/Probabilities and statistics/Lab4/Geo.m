function [y] = Geo(n,p)
    
    y = zeros(1,n);
    for i = 1:n
       nr = 0;
       while Bernn(p) == 0
           nr = nr+1;
       end
       y(i) = nr;
    end
end