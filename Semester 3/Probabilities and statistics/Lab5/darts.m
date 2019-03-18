clf;
t = 0:0.1:2*pi;
polar(t,4*ones(size(t)), '--k')
hold on;
rectangle('position',[-0.5,-0.5,1,1],'curvature',[1,1],'facecolor','r')

u = rand(1,20) 
v = rand(1,20)

x = sqrt(-2*log(u)).*cos(2*pi*v)
y = sqrt(-2*log(v)).*sin(2*pi*u)

plot(x,y,'*k')