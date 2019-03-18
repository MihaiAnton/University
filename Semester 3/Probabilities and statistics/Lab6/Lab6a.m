function I = Lab6a(g,a,b,n)
    I = mean(g(unifrnd(a,b,1,n))) * (b-a);
end