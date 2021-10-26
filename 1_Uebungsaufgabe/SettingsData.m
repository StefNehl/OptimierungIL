classdef SettingsData
    %SETTINGSDATA Summary of this class goes here
    %   Detailed explanation goes here
    
    properties
        R
        EPSILON
        FindMaximum
        MAX_NUMBER_OF_ITERATIONS
    end
    
    methods
        function obj = SettingsData(r,epsilon, findMaximum, maxNumberOfIterations)
            obj.R = r;
            obj.EPSILON = epsilon;
            obj.FindMaximum = findMaximum;
            obj.MAX_NUMBER_OF_ITERATIONS = maxNumberOfIterations;
        end
    end
end

