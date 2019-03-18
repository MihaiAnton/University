function I = Lab6b(g,a,b,n)

    X = unifrnd(a,b,1,n);
    M = max(g(X));
    Y = unifrnd(0,M,1,n);
    I = mean(Y < g(X)) * (b-a) * M;

end