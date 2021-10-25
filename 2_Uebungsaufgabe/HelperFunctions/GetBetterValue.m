function [betterValue] = GetBetterValue(x1, x2, maximum)
    if IsBetterValue(x1, x2, maximum)
        betterValue = x1;
    else
        betterValue = x2;
    end
end

%%
function [result] = IsBetterValue(x1, x2, maximum)
    if x1 > x2
        result = x1;
    else 
        result = x2;
    end

    if ~maximum
        result = ~result;
    end
end