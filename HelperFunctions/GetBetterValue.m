function [betterValue] = GetBetterValue(x1, x2, maximum)
    if IsBetterValue(x1, x2, maximum)
        betterValue = x1;
    else
        betterValue = x2;
    end
end