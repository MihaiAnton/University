function p = p2i(n,k,p)
    for i = 1:n
       pos = 0;
       dreapta = 0;
       for j = 1:k
          fprintf( '%d ', pos);
          dir = Bernn(p);
          if dir == 0
              pos = pos-1;
          else
              pos = pos +1;
          end
       
       end
       fprintf('\n'); 
    end

end