function [x] = goldensectionsearch(f, xu, xo, EPSILON, MAX_NUMBER_OF_ITERATIONS)
    %x = (xu + xo) / 2; %This line is here to make the code runnable and should not be included the final code.
    
    %your code
    count = 0;
    r = 0.6180339;
    findMaximum = true;
    x = 10000000;
    settingsData = SettingsData(r, EPSILON, findMaximum, MAX_NUMBER_OF_ITERATIONS);
    
    x1 = xo - settingsData.R * (xo - xu);
    x2 = xu + settingsData.R * (xo - xu);
    
    newParameterData = ParameterData(x1, x2, xu, xo);
    
    x = stepOne(newParameterData, count, settingsData);    
end

function [xOpt] = stepOne(parameterData, count, settingsData)       

    while count < settingsData.MAX_NUMBER_OF_ITERATIONS            
    
        xo = parameterData.Xo;
        xu = parameterData.Xu;
        
        x1 = xo - settingsData.R * (xo - xu);
        x2 = xu + settingsData.R * (xo - xu);

        parameterData.X1 = x1;
        parameterData.X2 = x2;
        
        if (xo - xu) <= settingsData.EPSILON
            [xOpt] = 0.5 * (xo + xu);
            break;
        elseif IsBetterValue(x1, x2, settingsData.FindMaximum)            
            parameterData = stepTwo(parameterData, settingsData.R);
        else
            parameterData = stepThree(parameterData, settingsData.R);
        end
               
        count = count + 1;
    end     
end

function parameterData = stepTwo(parameterData, r)
    parameterData.Xo = parameterData.X2;
    parameterData.X2 = parameterData.X1;
    parameterData.X1 = parameterData.Xo - r * (parameterData.Xo - parameterData.Xu);
end

function parameterData = stepThree(parameterData, r)
    parameterData.Xu = parameterData.X1; 
    parameterData.X1 = parameterData.X2; 
    parameterData.X2 = parameterData.Xu + r * (parameterData.Xo - parameterData.Xu);  
end