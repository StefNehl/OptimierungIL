function [result] = IsBetterValue(x1, x2, maximum)
    if x1 > x2
        result = true;
    else 
        result = false;
    end

    if ~maximum
        result = ~result;
    end
end