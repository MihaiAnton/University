function P4()
    
    x = [7, 7, 4, 5, 9, 9, 4, 12, 8, 1, 8, 7, 3, 13, 2, 1,17, 7, 12,5,6,2,1,13,14,10,2,4,9,11,3,5,12,6,10,7];
    
    sigma = 5;
    alpha1 = 0.05;
    alpha2 = 0.01;
    
    [H,P,CI,ZVAL] = ztest(x,9,sigma,alpha2,-1);
    
    disp(CI);


end