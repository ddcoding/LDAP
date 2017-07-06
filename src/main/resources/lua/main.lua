Car = {
    x = 0,
    mt = {},

    New = function()
    local v = {}

    setmetatable(v,Car.mt)

        v.x = Car.x
        v.Wypisz = Car.Wypisz
    -- I don't need above matches if I'll do __index = Car , cuz index allow us to get all values from parent
        return v
    end,

    Wypisz = function(self)
        print(self.x)
    end
}
Car.mt.__add = function(a,b)
    local vec = Car.New()
    vec.x = a.x + b.x
    return vec;
end

Car.mt.__eq = function(a,b)
    return a.x == b.x
end

Car.mt.__tostring = function(vec)
    return "(" .. vec.x .. ")"
end

Car.mt.__metatable = "Private" -- it's because we won't change metatable of classe's metatable . Then we need to protect it

--Car.mt__index = Car

v1 = Car.New()
v1.x=4

v2 = Car.New()
v2.x = 6

v3 = Car.New()
v3 = v1 + v2
v3:Wypisz()
print(v1 == v2) -- false of course
print(v3) -- toString test







--Comparators, lt less than , gt greater then , le, ge
-- div,mul,mod etc...