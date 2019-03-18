function [spam] = spamCheck()

    idspam = fopen('spam.txt','r');
    idnspam = fopen('notspam.txt','r');
    
    spam = textscan(idspam, '%s');
    notspam = textscan(idnspam, '%s');
    
    fclose(idspam);
    fclose(idnspam);
    
    mail = textscan('click to buy','%s');
    
    ps = numel(spam{1}) / (numel(spam{1}) + numel(notspam{1}));
    ph = numel(notspam{1}) / (numel(spam{1}) + numel(notspam{1})); 
    words = unique([spam{1}; notspam{1}]);
    
    for i = 1:numel(mail{1})
       if ismember(mail{1}{i}, words)
           
           ps = ps*mean(strcmp(mail{1}{i}, spam{1}));
           ph = ph*mean(strcmp(mail{1}{i}, notspam{1}));
           
       end     
    end  
    
    fprintf('Ham:  %501.500f\n',ph);
    fprintf('Spam: %501.500f\n',ps);
 

end