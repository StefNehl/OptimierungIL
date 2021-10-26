classdef ParameterData
    %PARAMETERDATA Summary of this class goes here
    %   Detailed explanation goes here
    
    properties
        X1
        X2
        Xu
        Xo
    end
    
    methods
        function obj = ParameterData(x1, x2, xu, xo)
            %PARAMETERDATA Construct an instance of this class
            %   Detailed explanation goes here
            obj.X1 = x1;
            obj.X2 = x2;
            obj.Xu = xu;
            obj.Xo = xo;
        end       
    end
end

