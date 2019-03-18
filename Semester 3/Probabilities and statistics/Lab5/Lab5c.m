

function N = Lab5c(a,n)

    N = zeros(1,n)
    T = 0;
    for i = 1:n
        T = 0;
        while T < 1
            T = T -log(rand(1,100))/a;
            N(i) = N(i) + 1;
        end
        
    end

end