
function [ i_out ] = PID_additive_2( J, J1, M, omega, a, t )
close all;
%PID_ADDITIVE 
%   INPUT VARIABLES:
%       J
%       J1
%       M
%       omega
%       a
%       t
%   OUTPUT VARIABLES:
%       i
%   INTERNAL VARIABLES:
%        alpha
%        e
%        delta_alpha
%         
%   Kp, Ki, Kd indicates three coefficients of PID

%preparation
Kp = 0.12;
Ki = -0.098;
Kd = 0.1;

%start
alpha(2) = abs((omega(2) - omega(1)) / t); 
alpha(3) = abs((omega(3) - omega(2)) / t); 
alpha(4) = abs((omega(4) - omega(3)) / t); 
alpha_out(3) = alpha(2);
alpha_out(4) = alpha(3);
alpha_out(5) = alpha(4);
e(3) = M(3) / J - alpha(3);
e(4) = M(4) / J - alpha(3);
delta_e(4) = e(4) - e(3);
for n = 5 : (length(M) - 4)
    alpha(n) = abs((omega(n) - omega(n-1)) / t);
    e(n) = M(n) / J - alpha(n);
    delta_e(n) = e(n) - e(n-1);
    delta_alpha(n) = Kp * delta_e(n) + Ki * e(n) + Kd * (delta_e(n) - delta_e(n-1));
    alpha_out(n+1) = alpha(n) + delta_alpha(n) * t;
    i_out(n+1) = J1 * a * abs(alpha_out(n+1));
    omega(n+1) = omega(n) - alpha_out(n+1) * t;
    JJ(n) = M(n) / alpha(n);
    R1 = rand;
    if(R1 > 0.5)
        omega(n+1) = omega(n+1) + rand * 0.0005;
    else
        omega(n+1) = omega(n+1) - rand * 0.0005;
    end
end

%plot diagraph
%plot(omega,'y');
%plot(delta_alpha,'g');
hold on;
grid on;

%plot(e,'b');
%plot(alpha_out,'b');
%plot(alpha,'cyan');
subplot(2,1,2);
plot(JJ(5:(length(M)-4)),'g');
xlabel('t / 10ms'); 
ylabel('J / kg*m^2');
grid on;
subplot(2,1,1);
plot(i_out(7:(length(M)-4)),'r');
xlabel('t / 10ms'); 
ylabel('i / A');
grid on;
%calculate delta
