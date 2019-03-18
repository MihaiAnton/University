function [l] = p2iii(n,k,p)
    
    l = zeros(1,n);
    for i=1:n
       pos = 0;
       for j=1:k
       
           if Bernn(p) == 1
               pos= pos+1;
           else
               pos = pos-1;
           end
       
       end
       l(i)=pos;
    end

end