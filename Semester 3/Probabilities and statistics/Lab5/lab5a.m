function X = lab5a(x, p, n)
%{
    prob = rand();
    P = 0;
    k = 1;
    while 1
       if prob >= P && prob < P+p(k)
            X = x(k);
            break
       end
       P = P + p(k);
       k = k+1;
    end
%}
    
    [~,~,X] = histcounts(rand(1,n), cumsum([0,p]));
    
end