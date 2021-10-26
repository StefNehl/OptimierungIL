function [xu, xmidpoint, xo] = threepointpatternsearchmethod(f, xu, delta, findMaximum)
    xmidpoint = xu; %Diese Zeile muss weggeloescht werden. (Sie ist hier nur, um den Code ausfuehrbar zu machen.)
    xo = xu; %Diese Zeile muss weggeloescht werden. (Sie ist hier nur, um den Code ausfuehrbar zu machen.)
    
    %Ihr Code
    [xu, xmidpoint, xo] = stepOne(f, xu, xmidpoint, xo, delta, findMaximum);
end

function [xu, xmidpoint, xo] = stepOne(f, xu, xmidpoint, xo, delta, findMaximum)
    % f(xu + delta) > f(xu)
    fValueDelta = f(xu + delta);
    fValue = f(xu);
  
    if(IsBetterValue(fValueDelta, fValue, findMaximum))
        xmidpoint = xu + delta;
        [xu, xmidpoint, xo] = stepTwo(f, xu, xmidpoint, xo,delta, findMaximum);
    else 
        xo = xu + delta;
        [xu, xmidpoint, xo] = stepThree(f, xu, xmidpoint, xo, delta, findMaximum);
    end
end

function [xu, xmidpoint, xo] = stepTwo(f, xu, xmidpoint, xo, delta, findMaximum)
    delta = 2 * delta;
    if(f(xmidpoint) > f(xmidpoint + delta))
        xo = xmidpoint + delta;
    else
        xu = xmidpoint;
        xmidpoint = xmidpoint + delta;
        [xu, xmidpoint, xo] = stepTwo(f, xu, xmidpoint, xo, delta, findMaximum);
    end
end

function [xu, xmidpoint, xo] = stepThree(f, xu, xmidpoint, xo, delta, findMaximum)
    delta = delta / 2;
    if(f(xu + delta) > f(xu))
        xmidpoint = xu + delta; 
    else
        xo = xu + delta;
        [xu, xmidpoint, xo] = stepThree(f, xu, xmidpoint, xo, delta, findMaximum);
    end
end