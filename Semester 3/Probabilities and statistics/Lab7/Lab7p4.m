function Lab7p4(n)

    % H0 v <= 4
    % H1 v > 4
    s = unifrnd(1,4);
    X = normrnd(400,s,1,n);
    [H,P,Cl,ZVAL]=vartest(X,4,0.01,'right');
    if H == 1
        disp('Respins'); 
    else
        disp('Acceptat');
    end
    disp(Cl);
    disp(P);

end