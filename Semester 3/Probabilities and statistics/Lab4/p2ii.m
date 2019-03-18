function p = p2ii(n,p)

    sum = 0;
    for i= 1:n
       
        sum2 = 0;
        while Bernn(p) == 1
            sum2 = sum2 + 1;
        end
        sum = sum + sum2;
    end
    p = sum/n;

end