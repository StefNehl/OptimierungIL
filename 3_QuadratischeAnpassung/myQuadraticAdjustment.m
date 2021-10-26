function [x] = myQuadraticAdjustment(f, xu, xmidpoint, xo, EPSILON, DELTA, MAX_NUMBER_OF_ITERATIONS, findMax)
    count = 0;
    
    newSettings = Settings(EPSILON, DELTA, MAX_NUMBER_OF_ITERATIONS, findMax);
    x = stepOne(f, xu, xmidpoint, xo, count, newSettings ); 
end

function [x] = stepOne(f, xu, xm, xo, count, settings)
    while ((xo - xu) >= settings.EPSILON || count < settings.MAX_NUMBER_OF_ITERATIONS)
        xq = getXqValue(f, xu, xm, xo);
        
        if abs(xq - xm) < settings.DELTA
            [xu, xm, xo, count] = stepThree(f, xu, xm, xo, count, settings);
        elseif xq < (xm - settings.DELTA)
            [xu, xm, xo, count] = stepFour(f, xu, xm, xo, xq, count, settings);
        elseif xq > (xm + settings.DELTA)
            [xu, xm, xo, count] = stepFive(f, xu, xm, xo, xq, count, settings);
        end        
        count = count + 1;
    end 
    x = xm;
end

function [xu, xm, xo, count] = stepThree(f, xu, xm, xo, count, settings)
    deltaXu = xm - xu;
    deltaXo = xo - xm;
    
    if(deltaXo < deltaXu)
        xq = xm - (settings.EPSILON/2);
        [xu, xm, xo, count] = stepFour(f, xu, xm, xo, xq, count, settings);
    else
        xq = xm + (settings.EPSILON/2);
        [xu, xm, xo, count] = stepFive(f, xu, xm, xo, xq, count, settings);
    end
end

function [xu, xm, xo, count] = stepFour(f, xm, xq, count, settings)
    if IsBetterValue(f(xm), f(xq), settings.FindMax)
        xu = xm;
        xm = xq; 
    else
        xo = xm; 
        xm = xq;         
    end    

end

function [xu, xm, xo, count] = stepFive(f, xu, xm, xo, xq, count, settings)
    if IsBetterValue(f(xm), f(xq), settings.FindMax)
        xo = xq;        
    else
        xu = xm;
        xm = xq; 
    end 
end

function [x] = getXqValue(f, xu, xm, xo)
    su = xu^2;
    sm = xm^2;
    so = xo^2;
    
    x = 0.5 * ((f(xu)*(sm - so) + f(xm)*(so - su) + f(xo)*(su - sm)) / (f(xu)*(xm - xo) + f(xm)*(xo - xu) + f(xo)*(xu - xm)));
end