classdef Settings
    %SETTINGSDATA Summary of this class goes here
    %   Detailed explanation goes here
    
    properties
        EPSILON
        DELTA
        MAX_NUMBER_OF_ITERATIONS
        FindMax
    end
    
    methods
        function obj = Settings(epsilon,delta, maxNumberOfIterations, findMax)
            obj.EPSILON = epsilon;
            obj.DELTA = delta;
            obj.MAX_NUMBER_OF_ITERATIONS = maxNumberOfIterations;
            obj.FindMax = findMax;
        end       
    end
end

