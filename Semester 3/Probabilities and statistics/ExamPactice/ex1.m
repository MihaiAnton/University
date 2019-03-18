function p = ex1(n)

    succes = 0;
    for i = 1:n
        a = randi([0 30]);
        b = randi([30-a 30]);
        
        s = a + b;
        if s == 30
            succes = succes + 1;
            
        end
      
    end
    
    p = succes/n;
end