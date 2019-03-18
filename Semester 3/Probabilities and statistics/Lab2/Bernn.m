function bit = Bernn(p)
    r = rand();
    if r < p
        bit = 0;
    else
        bit = 1;
    end
end