function x = Lab7()
    
    arr = [ones(1,25) * 343,ones(1,15) * 345,ones(1,132) * 347,ones(1,84) * 348,ones(1,40) * 349,ones(1,34) * 350,ones(1,51) * 352,ones(1,8) * 353,ones(1,1) * 355];

    n = length(arr);
    
    mx = mean(arr);
    
    alpha = 0.05;
    sigma = 2;
    
    [H,P,Cl,ZVAL] = ztest(arr,mx,sigma,alpha)
    
    if H == 1
        dips('respins');
    else
        disp('acceptat');
    end
    
    disp(Cl);
end