clear; clc; clf;

%% Plot Ordinary vs Karatsuba

load results.txt

n = results(:,1)
ratio = results(:,2)
tKar = results(:,3)
tNorm = results(:,4)

% Ratio figure
figure(1);
plot(n,ratio,'*')
hold on
plot([0 20],[1 1], 'r')
xlabel('Polynomial degree $2^n$','interpreter','latex','fontsize',16);
ylabel('Ratio (naive/Karatsuba)','interpreter','latex','fontsize',14);
title('Ratio of time for naive polynomial multiplication vs Karatsuba','interpreter','latex','fontsize',14)
grid; axis([0 20 0 10]);
%legend({'Ratio $\frac{t-{normal}}{t-{Karatsuba}}$'},'interpreter','latex','fontsize',14);

fig = gcf;
fig.PaperUnits = 'inches';
fig.PaperPosition = [0 0 6 3];
fig.PaperPositionMode = 'manual';
print -djpeg100 ratio

% Karatsuba vs Normal
figure(2);
semilogy(n,tKar,'r');
hold on
semilogy(n, tNorm);
xlabel('Polynomial degree $2^n$','interpreter','latex','fontsize',16);
ylabel('Time (ns)','interpreter','latex','fontsize',14);
title('Computational time for naive polynomial multiplication vs Karatsuba','interpreter','latex','fontsize',14)
grid; %axis([0 20 0 1.3]);
%legend({'Ratio $\frac{t-{normal}}{t-{Karatsuba}}$'},'interpreter','latex','fontsize',14);

fig = gcf;
fig.PaperUnits = 'inches';
fig.PaperPosition = [0 0 6 3];
fig.PaperPositionMode = 'manual';
print -djpeg100 time
