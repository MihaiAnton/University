function [prob] = loto(n)
    prob = zeros(1,7);
    win = randsample(1:49,6,false);
    
    for i = 1:n
        ticket = randsample(1:49,6,false);
        x = ismember(win,ticket);
        c = sum(x);
        prob(c+1) = prob(c+1) + 1;
        
    end


end