function nr = NBin(n,p)
    succes = 0;
    insucces = 0;
    while succes < n
        nr = Bernn(p);
        if nr == 0
            insucces = insucces+1;
        else
           succes = succes + 1;
        end            
    end
    
    nr = insucces;
end