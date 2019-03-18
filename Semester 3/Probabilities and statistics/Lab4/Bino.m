function [l,prob] = Bino(n,p)

    l = zeros(1,n);
    for i = 1:n
       l(i) = Bernn(p); 
    end
    prob = sum(l)/n;


end