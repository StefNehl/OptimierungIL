function [x] = myGoldensectionsearch(f, xu, xo, EPSILON, MAX_NUMBER_OF_ITERATIONS)
    %x = (xu + xo) / 2; %This line is here to make the code runnable and should not be included the final code.
    
    %your code
    count = 0;
    r = 0.6180339;
    findMaximum = true;
    settingsData = SettingsData(r, EPSILON, findMaximum, MAX_NUMBER_OF_ITERATIONS);     
    
    x = stepOne(f, xu, xo, count, settingsData);    
end

function [xOpt] = stepOne(f, xu, xo, count, settingsData)       

    x1 = xo - settingsData.R * (xo - xu);    
    x2 = xu + settingsData.R * (xo - xu);
    
    while count < settingsData.MAX_NUMBER_OF_ITERATIONS                                    
        
        if (xo - xu) <= settingsData.EPSILON
            [xOpt] = 0.5 * (xo + xu);
            return;
        elseif IsBetterValue(f(x1), f(x2), settingsData.FindMaximum)            
            xo = x2;
            x2 = x1;
            x1 = xo - settingsData.R * (xo - xu);
        else
            xu = x1; 
            x1 = x2; 
            x2 = xu + settingsData.R * (xo - xu);  
        end
               
        count = count + 1;
    end   
    [xOpt] = NaN;
end