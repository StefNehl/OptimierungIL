function [xu, xmidpoint, xo] = threepointpatternsearchmethod(f, xu, delta)
    xmidpoint = xu; %Diese Zeile muss weggeloescht werden. (Sie ist hier nur, um den Code ausfuehrbar zu machen.)
    xo = xu; %Diese Zeile muss weggeloescht werden. (Sie ist hier nur, um den Code ausfuehrbar zu machen.)
    
    %Ihr Code
    [xu, xmidpoint, xo] = stepOne(f, xu, xmidpoint, xo, delta);
end

%%
function [xu, xmidpoint, xo] = stepOne(f, xu, xmidpoint, xo, delta)
    if(f(xu + delta) > f(xu))
        xmidpoint = xu + delta;
        [xu, xmidpoint, xo] = stepTwo(f, xu, xmidpoint, xo,delta);
    else 
        xo = xu + delta;
        [xu, xmidpoint, xo] = stepThree(f, xu, xmidpoint, xo, delta);
    end
end

function [xu, xmidpoint, xo] = stepTwo(f, xu, xmidpoint, xo, delta)
    delta = 2 * delta;
    if(f(xmidpoint) > f(xmidpoint + delta))
        xo = xmidpoint + delta;
    else
        xu = xmidpoint;
        xmidpoint = xmidpoint + delta;
        [xu, xmidpoint, xo] = stepTwo(f, xu, xmidpoint, xo, delta);
    end
end

function [xu, xmidpoint, xo] = stepThree(f, xu, xmidpoint, xo, delta)
    delta = delta / 2;
    if(f(xu + delta) > f(xu))
        xmidpoint = xu + delta; 
    else
        xo = xu + delta;
        [xu, xmidpoint, xo] = stepThree(f, xu, xmidpoint, xo, delta);
    end
end